package org.shaq.graphql.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: shakedvit
 * Date: 25/07/18
 * Time: 08:13
 * To change this template use File | Settings | File Templates.
 */
public class GraphQLQueryGeneratorException extends RuntimeException {

    public GraphQLQueryGeneratorException() {
        super();
    }

    public GraphQLQueryGeneratorException(String message) {
        super(message);
    }
}
