package org.shaq.plugins.exceptions;

public class GraphQLSchemaParseException extends RuntimeException {

    public GraphQLSchemaParseException() {
    }

    public GraphQLSchemaParseException(String message) {
        super(message);
    }

    public GraphQLSchemaParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
