package org.shaq.plugins.models.graphql;

import lombok.Data;

import java.util.HashMap;

@Data
public class GraphQLGenerationContext {

    private HashMap <String, GraphQLOperation> queries;
    private HashMap <String, GraphQLOperation> mutations;
    private HashMap <String, GraphQLSimpleType> types;
    private HashMap <String, GraphQLSimpleType> scalars;

}
