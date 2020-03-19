package org.shaq.plugins.models;

import org.shaq.plugins.models.graphql.GraphQLSchema;
import lombok.Data;

@Data
public class GenerationModel {

    private String rootClassName;
    private GraphQLSchema graphqlSchema;

}
