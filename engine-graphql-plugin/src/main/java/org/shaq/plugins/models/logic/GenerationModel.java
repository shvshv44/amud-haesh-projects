package org.shaq.plugins.models.logic;

import lombok.Data;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;

@Data
public class GenerationModel {

    private String rootClassName;
    private GraphQLGenerationContext generationContext;

}
