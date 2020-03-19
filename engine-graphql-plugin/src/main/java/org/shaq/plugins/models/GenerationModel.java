package org.shaq.plugins.models;

import org.shaq.plugins.models.graphql.GraphqlSchema;
import lombok.Data;

@Data
public class GenerationModel {

    private String rootClassName;
    private GraphqlSchema graphqlSchema;

}
