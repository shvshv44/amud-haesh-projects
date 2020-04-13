package org.shaq.plugins.exceptions;

public class GraphQLClassGenerationException extends RuntimeException {

    public GraphQLClassGenerationException() {
        super();
    }

    public GraphQLClassGenerationException(String message) {
        super(message);
    }

    public GraphQLClassGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
