package org.shaq.plugins.models.user;

import lombok.Data;
import org.shaq.plugins.models.graphql.GraphQLField;

import java.util.List;

@Data
public class GraphQLChoosenField {

    private String name;
    private GraphQLField originalField;
    private List<GraphQLChoosenField> innerChoosenFields;

}
