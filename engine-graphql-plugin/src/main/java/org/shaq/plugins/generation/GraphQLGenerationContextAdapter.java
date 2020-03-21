package org.shaq.plugins.generation;

import graphql.language.*;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.shaq.plugins.exceptions.GraphQLSchemaParseException;
import org.shaq.plugins.models.graphql.GraphQLField;
import org.shaq.plugins.models.graphql.GraphQLFieldType;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;
import org.shaq.plugins.models.graphql.GraphQLSimpleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphQLGenerationContextAdapter {

    public GraphQLGenerationContext adapt(TypeDefinitionRegistry registry) {
        GraphQLGenerationContext context = new GraphQLGenerationContext();

        buildSimpleTypes(context, registry.types());
        buildTypeExtensions(context, registry.types());
        buildFields(context, registry.types()); // TODO: implement deal with scalars

        return context;
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
        if (coreType == null)
            throw new GraphQLSchemaParseException(core + " type does not exist in schema!");

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
            GraphQLSimpleType simpleType = adaptTypeDefinitionToSimpleType(typeDefinition);
            context.getTypes().put(simpleType.getName(), simpleType);
        }

    }

    private GraphQLSimpleType adaptTypeDefinitionToSimpleType(TypeDefinition typeDefinition) {

        GraphQLSimpleType simpleType = new GraphQLSimpleType();
        simpleType.setName(typeDefinition.getName());
        simpleType.setFields(new HashMap<>());
        simpleType.setInheritedTypes(new HashMap<>());

        return simpleType;
    }

}
