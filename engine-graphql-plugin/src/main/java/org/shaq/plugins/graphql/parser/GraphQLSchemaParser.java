package org.shaq.plugins.graphql.parser;

import org.shaq.plugins.models.graphql.GraphQLOperation;
import org.shaq.plugins.models.graphql.GraphQLSchema;
import org.shaq.plugins.models.graphql.GraphQLType;
import org.shaq.plugins.models.graphql.enums.GraphqlOperationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphQLSchemaParser {

    public GraphQLSchema parse(String schema) {
        GraphQLSchema graphqlSchema = initializeSchema();

        List<String> types = splitSchemaToTypes(schema);
        for (String typeAsString : types) {
            addToSchema(graphqlSchema,typeAsString);
        }

        return graphqlSchema;
    }

    private void addToSchema(GraphQLSchema graphqlSchema, String typeAsString) {
        String typeName = getTypeNameFromTypeString(typeAsString);
        switch (typeName) {
            case "Query": operateOperationType(graphqlSchema, typeAsString, GraphqlOperationType.QUERY); break;
            case "Mutation": operateOperationType(graphqlSchema, typeAsString, GraphqlOperationType.MUTATION); break;
            default: operateNonOperationalType(graphqlSchema, typeAsString); break;
        }
    }

    private void operateNonOperationalType(GraphQLSchema graphqlSchema, String typeAsString) {

    }

    private void operateOperationType(GraphQLSchema graphqlSchema, String typeAsString, GraphqlOperationType operationType) {

        String [] fields = getTypesFields(removeUnnecesserySpaces(typeAsString));
        for(String fieldAsString : fields) {
            String [] fieldProperties = fieldAsString.split(":");
            //TODO: deal with parameters
            GraphQLOperation operation = new GraphQLOperation();
            operation.setName(fieldProperties[0]);
            operation.setType(operationType);
            //TODO: deal with returnEntity
        }
    }

    private String removeUnnecesserySpaces(String string) {
        string = string.replaceAll("((\\s)+)?\\[((\\s)+)?", "["); // remove spaces [
        string = string.replaceAll("((\\s)+)?\\]", "]"); // remove spaces before ]
        string = string.replaceAll("((\\s)+)?:((\\s)+)?", ":"); // remove spaces :
        string = string.replaceAll("\\s+", " "); // remove unnecessary spaces

        return string;
    }


    private String getTypeSimpleName(String typeAsString) {
        String patternStr = "(\\w)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(typeAsString);
        if(matcher.find()){
            return matcher.group();
        }

        return "";
    }

    private String[] getTypesFields(String typeAsString) {
        String fieldsAsString = typeAsString.substring(typeAsString.indexOf('{') + 1, typeAsString.lastIndexOf('}')).trim();
        return fieldsAsString.split(" ");
    }

    private String getTypeNameFromTypeString(String typeAsString) {
        String startOfType = typeAsString.substring(0,typeAsString.indexOf("}"));
        String patternStr = "(\\b((?!(type))\\w)+\\b)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(startOfType);
        if(matcher.find()){
            return matcher.group();
        }

        return "";
    }

    private List<String> splitSchemaToTypes(String schema) {

        String currentSchema = schema.trim();
        List<String> result = new ArrayList<>();

        int nextTypeIndex = findNextTypeIndex(currentSchema);
        while (nextTypeIndex != -1) {

            int startIndex = nextTypeIndex;
            int endIndex = nextTypeIndex;
            char [] restOfTheString = currentSchema.toCharArray();
            while (endIndex <= restOfTheString.length && restOfTheString[endIndex] != '}') {
                endIndex++;
            }
            result.add(currentSchema.substring(startIndex,endIndex + 1));
            currentSchema = currentSchema.substring(endIndex + 1);

            nextTypeIndex = findNextTypeIndex(currentSchema);
        }

        return result;
    }

    private int findNextTypeIndex(String schema) {
        String patternStr = "(type \\w+( +)?\\{)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(schema);
        if(matcher.find()){
           return matcher.start();
        }

        return -1;
    }

    private GraphQLSchema initializeSchema() {
        GraphQLSchema schema = new GraphQLSchema();
        schema.setOperations(new HashMap<>());
        schema.setTypes(new HashMap<>());
        return schema;
    }


}
