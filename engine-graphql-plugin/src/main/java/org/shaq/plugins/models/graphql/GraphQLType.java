package org.shaq.plugins.models.graphql;

import lombok.Data;

import java.util.List;

@Data
public class GraphQLType {

    private String name;
    private List<GraphQLType> inheritedTypes;
    private List<GraphQLField> fields;

}
