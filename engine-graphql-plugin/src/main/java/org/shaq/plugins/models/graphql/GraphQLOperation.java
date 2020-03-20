package org.shaq.plugins.models.graphql;

import lombok.Data;
import org.shaq.plugins.models.graphql.enums.GraphQLOperationType;

@Data
public class GraphQLOperation {

    private String name;
    private GraphQLOperationType type;
    private GraphQLFieldType returnType;

}
