package org.shaq.plugins.generation;

import graphql.language.*;
import graphql.schema.idl.TypeDefinitionRegistry;
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
        buildFields(context, registry.types());

        return context;
    }

    private void buildFields(GraphQLGenerationContext context, Map<String, TypeDefinition> registryTypes) {

        for(TypeDefinition typeDefinition : registryTypes.values()) {

            GraphQLSimpleType simpleType = context.getTypes().get(typeDefinition.getName());
            if (typeDefinition instanceof ObjectTypeDefinition) {
                dealWithTypeFields(simpleType, ((ObjectTypeDefinition) typeDefinition).getFieldDefinitions());
            } else if (typeDefinition instanceof InterfaceTypeDefinition) {
                dealWithTypeFields(simpleType, ((InterfaceTypeDefinition) typeDefinition).getFieldDefinitions());
            }

        }

    }

    private void dealWithTypeFields(GraphQLSimpleType simpleType, List<FieldDefinition> typeFieldDefinitions) {
        for (FieldDefinition fieldDefinition : typeFieldDefinitions) {
            addFieldFromDefinition(simpleType, fieldDefinition);
        }
    }

    private void addFieldFromDefinition(GraphQLSimpleType simpleType, FieldDefinition fieldDefinition) {

        //TODO: implement

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
