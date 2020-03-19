package org.shaq.plugins.models.graphql;


import lombok.Data;

@Data
public class GraphqlParameter {

    private String name;
    private GraphqlType type;
    private Boolean isNullable;

}
