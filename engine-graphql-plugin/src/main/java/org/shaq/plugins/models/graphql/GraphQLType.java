package org.shaq.plugins.models.graphql;

import java.util.List;

public class GraphQLType {

    private String name;
    private Boolean isCollection;
    private List<GraphQLType> inheritedTypes;
    private List<GraphQLField> fields;

}
