package org.shaq.plugins.generation;

import graphql.language.*;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.shaq.plugins.exceptions.GraphQLSchemaParseException;
import org.shaq.plugins.models.graphql.*;
import org.shaq.plugins.models.graphql.enums.GraphQLOperationType;

import java.util.*;

public class GraphQLGenerationContextAdapter {

    public GraphQLGenerationContext adapt(TypeDefinitionRegistry registry) {
        GraphQLGenerationContext context = new GraphQLGenerationContext();

        buildScalars(context);
        buildSimpleTypes(context, registry.types());
        buildTypeExtensions(context, registry.types());
        buildFields(context, registry.types());
        buildQueries(context, registry.schemaDefinition());
        buildMutations(context, registry.schemaDefinition());

        if (context.getQueries().size() == 0 && context.getMutations().size() == 0)
            throw new GraphQLSchemaParseException("Cannot generate GraphQL Schema POJOs without at least one query or mutation definition!");

        return context;
    }

    private void buildMutations(GraphQLGenerationContext context, Optional<SchemaDefinition> schemaDefinitionOpt) {
        context.setMutations(new HashMap<>());
        HashMap<String, GraphQLOperation> mutations = buildOperations(context,schemaDefinitionOpt,GraphQLOperationType.MUTATION);

        if (mutations != null && mutations.values().size() > 0) {
            context.setMutations(mutations);
        }
    }

    private void buildQueries (GraphQLGenerationContext context, Optional<SchemaDefinition> schemaDefinitionOpt) {
        context.setQueries(new HashMap<>());
        HashMap<String, GraphQLOperation> queries = buildOperations(context,schemaDefinitionOpt,GraphQLOperationType.QUERY);

        if (queries != null && queries.values().size() > 0) {
            context.setQueries(queries);
        }
    }

    private HashMap<String, GraphQLOperation> buildOperations (GraphQLGenerationContext context, Optional<SchemaDefinition> schemaDefinitionOpt, GraphQLOperationType type) {
        HashMap<String, GraphQLOperation> operations = new HashMap<>();
        GraphQLSimpleType operationType = getOperationTypeFromContext(context, schemaDefinitionOpt, type);
        if (operationType != null) {

            for (GraphQLField field : operationType.getFields().values()) {
                GraphQLOperation operation = new GraphQLOperation();
                operation.setName(field.getName());
                operation.setType(type);
                operation.setReturnType(field.getType());
                operations.put(operation.getName(), operation);
            }

            context.getTypes().remove(operationType.getName());
        }

        return operations;
    }

    private GraphQLSimpleType getOperationTypeFromContext(GraphQLGenerationContext context, Optional<SchemaDefinition> schemaDefinitionOpt, GraphQLOperationType type) {

        String queryTypeName = type.getDefaultType();
        if (schemaDefinitionOpt.isPresent()) {
            SchemaDefinition schemaDefinition = schemaDefinitionOpt.get();
            for (OperationTypeDefinition operationTypeDefinition : schemaDefinition.getOperationTypeDefinitions()) {
                if (operationTypeDefinition.getName().equals(type.getNameInSchema())) {
                    queryTypeName = operationTypeDefinition.getTypeName().getName();
                }
            }
        }

        return context.getTypes().get(queryTypeName);
    }

    private void buildFields(GraphQLGenerationContext context, Map<String, TypeDefinition> registryTypes) {

        for(TypeDefinition typeDefinition : registryTypes.values()) {

            GraphQLSimpleType simpleType = context.getTypes().get(typeDefinition.getName());
            if (typeDefinition instanceof ObjectTypeDefinition) {
                dealWithTypeFields(context, simpleType, ((ObjectTypeDefinition) typeDefinition).getFieldDefinitions());
            } else if (typeDefinition instanceof InterfaceTypeDefinition) {
                dealWithTypeFields(context, simpleType, ((InterfaceTypeDefinition) typeDefinition).getFieldDefinitions());
            }

        }

    }

    private void dealWithTypeFields(GraphQLGenerationContext context, GraphQLSimpleType simpleType, List<FieldDefinition> typeFieldDefinitions) {
        for (FieldDefinition fieldDefinition : typeFieldDefinitions) {
            addFieldFromDefinition(context, simpleType, fieldDefinition);
        }
    }

    private void addFieldFromDefinition(GraphQLGenerationContext context, GraphQLSimpleType simpleType, FieldDefinition fieldDefinition) {
        GraphQLField field = new GraphQLField();
        field.setName(fieldDefinition.getName());
        GraphQLFieldType fieldType = createFieldType(context, fieldDefinition.getType());
        field.setType(fieldType);
        simpleType.getFields().put(field.getName(), field);
    }

    private GraphQLFieldType createFieldType(GraphQLGenerationContext context, Type type) {

        if(type instanceof TypeName) {
            return createFieldTypeFromTypeName(context,((TypeName)type).getName(), false, true);
        }
        if(type instanceof NonNullType) {
            return createFieldTypeFromNonTypeName(context, ((NonNullType)type).getType() , false, false);
        }
        if(type instanceof ListType) {
            return createFieldTypeFromNonTypeName(context, ((ListType)type).getType() , true, true);
        }

        throw new GraphQLSchemaParseException(type.getClass() + " is not implemented in this current version parser!");
    }

    private GraphQLFieldType createFieldTypeFromNonTypeName(GraphQLGenerationContext context, Type innerType, boolean isCollection, boolean isNullable) {
        if(innerType instanceof TypeName) {
            return createFieldTypeFromTypeName(context,((TypeName)innerType).getName(), isCollection, isNullable);
        }
        if(innerType instanceof NonNullType) {
            return createFieldTypeFromNonTypeName(context, ((NonNullType)innerType).getType() , isCollection, false);
        }
        if(innerType instanceof ListType) {
            return createFieldTypeFromNonTypeName(context, ((ListType)innerType).getType() , true, isNullable);
        }

        throw new GraphQLSchemaParseException(innerType.getClass() + " is not implemented in this current version parser!");
    }

    private GraphQLFieldType createFieldTypeFromTypeName(GraphQLGenerationContext context, String core, boolean isCollection, boolean isNullable) {
        GraphQLSimpleType coreType = context.getTypes().get(core);
        if (coreType == null) {
            coreType = context.getScalars().get(core);
            if (coreType == null) {
                throw new GraphQLSchemaParseException(core + " type does not exist in schema!");
            }
        }

        GraphQLFieldType graphQLFieldType = new GraphQLFieldType();
        graphQLFieldType.setCoreType(coreType);
        graphQLFieldType.setIsCollection(isCollection);
        graphQLFieldType.setIsNullable(isNullable);
        return graphQLFieldType;
    }


    private void buildTypeExtensions(GraphQLGenerationContext context, Map<String, TypeDefinition> registryTypes) {
        for(TypeDefinition typeDefinition : registryTypes.values()) {
            if(typeDefinition instanceof ObjectTypeDefinition) {
                ObjectTypeDefinition objectTypeDefinition = (ObjectTypeDefinition) typeDefinition;
                if(objectTypeDefinition.getImplements() != null && !objectTypeDefinition.getImplements().isEmpty()) {

                    GraphQLSimpleType implementType = context.getTypes().get(objectTypeDefinition.getName());
                    for (Type type : objectTypeDefinition.getImplements()) {
                        if(type instanceof TypeName) {
                            TypeName typeName = (TypeName)type;
                            GraphQLSimpleType implementedType = context.getTypes().get(typeName.getName());
                            if(implementedType != null) {
                                implementedType.getInheritedTypes().put(implementType.getName(), implementType);
                            }
                        }
                    }

                }
            }
        }
    }

    private void buildSimpleTypes(GraphQLGenerationContext context, Map<String, TypeDefinition> registryTypes) {
        context.setTypes(new HashMap<>());
        for(TypeDefinition typeDefinition : registryTypes.values()) {
            GraphQLSimpleType simpleType = createEmptySimpleType(typeDefinition.getName());
            context.getTypes().put(simpleType.getName(), simpleType);
        }
    }

    private GraphQLSimpleType createEmptySimpleType(String typeName) {

        GraphQLSimpleType simpleType = new GraphQLSimpleType();
        simpleType.setName(typeName);
        simpleType.setFields(new HashMap<>());
        simpleType.setInheritedTypes(new HashMap<>());

        return simpleType;
    }

    private void buildScalars(GraphQLGenerationContext context) {
        context.setScalars(new HashMap<>());
        String [] scalars = {"String","Int","Float","Boolean"};

        for (String scalarName : scalars) {
            context.getScalars().put(scalarName, createEmptySimpleType(scalarName));
        }
    }

}
