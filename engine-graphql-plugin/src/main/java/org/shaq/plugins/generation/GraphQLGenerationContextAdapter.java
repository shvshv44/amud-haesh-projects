package org.shaq.plugins.generation;

import graphql.schema.idl.TypeDefinitionRegistry;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;

public class GraphQLGenerationContextAdapter {

    public GraphQLGenerationContext adapt(TypeDefinitionRegistry registry) {
        GraphQLGenerationContext context = new GraphQLGenerationContext();

        //TODO: implement        

        return context;
    }

}
