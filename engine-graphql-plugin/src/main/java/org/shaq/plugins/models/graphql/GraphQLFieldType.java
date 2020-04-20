package org.shaq.plugins.models.graphql;

import lombok.Data;

@Data
public class GraphQLFieldType {

    private GraphQLSimpleType coreType;
    private Boolean isNullable;
    private Boolean isCollection;

}
