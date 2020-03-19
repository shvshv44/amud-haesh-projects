package org.shaq.plugins.models.graphql;


import lombok.Data;

@Data
public class GraphQLParameter {

    private String name;
    private GraphQLType type;
    private Boolean isNullable;

}
