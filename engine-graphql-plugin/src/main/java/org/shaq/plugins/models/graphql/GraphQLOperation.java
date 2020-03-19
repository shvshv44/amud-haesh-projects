package org.shaq.plugins.models.graphql;

import org.shaq.plugins.models.graphql.enums.GraphqlOperationType;
import lombok.Data;

import java.util.List;


@Data
public class GraphQLOperation {

    private String name;
    private GraphqlOperationType type;
    private List<GraphQLParameter> parameters;
    private GraphQLField returnEntity;

}
