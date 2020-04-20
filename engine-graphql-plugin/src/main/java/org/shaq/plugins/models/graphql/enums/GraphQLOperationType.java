package org.shaq.plugins.models.graphql.enums;

public enum GraphQLOperationType {

    QUERY ("query", "Query"),
    MUTATION ("mutation", "Mutation");

    GraphQLOperationType(String nameInSchema, String defaultType) {
        this.nameInSchema = nameInSchema;
        this.defaultType = defaultType;
    }

    private String nameInSchema;
    private String defaultType;

    public String getNameInSchema() {
        return nameInSchema;
    }

    public String getDefaultType() {
        return defaultType;
    }
}
