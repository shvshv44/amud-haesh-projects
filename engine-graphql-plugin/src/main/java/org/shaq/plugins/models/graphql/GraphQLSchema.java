package org.shaq.plugins.models.graphql;

import lombok.Data;

import java.util.HashMap;


@Data
public class GraphQLSchema {

    private HashMap <String , GraphQLOperation> operations;
    private HashMap <String , GraphQLType> types;

}
