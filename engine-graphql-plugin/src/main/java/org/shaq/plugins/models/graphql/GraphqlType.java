package org.shaq.plugins.models.graphql;

import java.util.List;

public class GraphqlType {

    private String name;
    private Boolean isCollection;
    private List<GraphqlType> inheritedTypes;

}
