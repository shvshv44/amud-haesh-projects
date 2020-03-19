package org.shaq.plugins.models.graphql.abstruct;

import lombok.Data;
import org.shaq.plugins.models.graphql.GraphQLType;

@Data
public abstract class GraphQLEntity {

    private String name;
    private GraphQLType type;
    private Boolean isNullable;
    private Boolean isCollection;

}
