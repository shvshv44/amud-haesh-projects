package org.shaq.plugins.models.graphql;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class GraphQLField {

    private String name;
    private GraphQLFieldType type;
    private HashMap<String, GraphQLField> inputs;
}
