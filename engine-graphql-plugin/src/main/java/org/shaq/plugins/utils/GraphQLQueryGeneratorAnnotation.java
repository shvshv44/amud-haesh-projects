package org.shaq.plugins.utils;

public enum GraphQLQueryGeneratorAnnotation {

    QUERY("GraphQLQuery", "iaf.ofek.skypath.graphql.annotations"),
    EXCLUDE_FIELD("GraphQLQueryExcludeField", "iaf.ofek.skypath.graphql.annotations"),
    INHERITED_OBJECT("GraphQLQueryInheritedObject", "iaf.ofek.skypath.graphql.annotations"),
    INNER_OBJECT("GraphQLQueryInnerObject", "iaf.ofek.skypath.graphql.annotations"),
    QUERY_OBJECT("GraphQLQueryObject", "iaf.ofek.skypath.graphql.annotations"),
    PARAMERITERIZED_OBJECT("GraphQLQueryPrameterizedObject", "iaf.ofek.skypath.graphql.annotations"),
    QUERY_TYPE("GraphQLQueryType", "iaf.ofek.skypath.graphql.annotations"),
    SERIALIZED_NAME("GraphQLSerializedName", "iaf.ofek.skypath.graphql.annotations"),
    PARAMETER("GraphQLParameter", "iaf.ofek.skypath.graphql.annotations.parameters"),
    PARAMETER_OBJECT("GraphQLParameterObject", "iaf.ofek.skypath.graphql.annotations.parameters"),
    PARAMETERS_CLASS("GraphQLParametersClass", "iaf.ofek.skypath.graphql.annotations.parameters")
    ;

    private String annotName;
    private String annotPackage;

    GraphQLQueryGeneratorAnnotation(String annotName, String annotPackage) {
        this.annotName = annotName;
        this.annotPackage = annotPackage;
    }

    public String getAnnotName() {
        return annotName;
    }

    public String getAnnotPackage() {
        return annotPackage;
    }
}
