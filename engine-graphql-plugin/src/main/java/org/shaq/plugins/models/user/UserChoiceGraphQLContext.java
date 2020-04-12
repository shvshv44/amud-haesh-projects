package org.shaq.plugins.models.user;

import lombok.Data;
import org.shaq.plugins.models.graphql.GraphQLOperation;
import org.shaq.plugins.models.graphql.GraphQLParameter;
import org.shaq.plugins.models.graphql.GraphQLSimpleType;

import java.util.HashMap;

@Data
public class UserChoiceGraphQLContext {

    private GraphQLOperation choosenOperation;
    private HashMap <String, GraphQLParameter> choosenParameters;
    private HashMap <String, GraphQLSimpleType> types;
    private GraphQLChoosenField rootField;
    private boolean lombokSupport;
    private boolean serializedNameSupport;

}
