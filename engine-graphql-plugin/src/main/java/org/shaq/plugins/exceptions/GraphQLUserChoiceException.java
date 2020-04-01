package org.shaq.plugins.exceptions;

public class GraphQLUserChoiceException extends RuntimeException {

    public GraphQLUserChoiceException() {
    }

    public GraphQLUserChoiceException(String message) {
        super(message);
    }

    public GraphQLUserChoiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
