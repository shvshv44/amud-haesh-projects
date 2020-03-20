package org.shaq.plugins.models.graphql;

import lombok.Data;

@Data
public class GraphQLField {

    private String name;
    private GraphQLFieldType type;
}
