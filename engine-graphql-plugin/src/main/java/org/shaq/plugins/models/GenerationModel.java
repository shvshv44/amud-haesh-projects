package org.shaq.plugins.models;

import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.Data;

@Data
public class GenerationModel {

    private String rootClassName;
    private TypeDefinitionRegistry graphqlSchema;

}
