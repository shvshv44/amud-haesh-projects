package org.shaq.plugins.models.graphql;

import lombok.Data;

import java.util.HashMap;

@Data
public class GraphQLSimpleType {

    private String name;
    private Boolean isScalar;
    private Boolean isEnum;
    private HashMap <String, GraphQLSimpleType> inheritedTypes;
    private HashMap <String, GraphQLField> fields;

}
