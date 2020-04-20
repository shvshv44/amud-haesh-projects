package org.shaq.plugins.models.graphql;

import lombok.Data;
import org.shaq.plugins.models.graphql.enums.GraphQLOperationType;

import java.util.HashMap;

@Data
public class GraphQLOperation {

    private String name;
    private GraphQLOperationType type;
    private GraphQLFieldType returnType;
    private HashMap<String, GraphQLParameter> parameters;

}
