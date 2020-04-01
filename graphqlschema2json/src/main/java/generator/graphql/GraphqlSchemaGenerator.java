package generator.graphql;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import generator.api.GraphqlImplementation;
import generator.api.UserDefaults;
import generator.graphql.manipulators.GraphqlSchemaTextManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphqlSchemaGenerator {

    public GraphqlSchemaTextManipulator textManipulator;
    private UserDefaults defaults;
    private List<GraphqlImplementation> graphqlImplementationList;

    @Autowired
    public GraphqlSchemaGenerator(GraphqlSchemaTextManipulator textManipulator) {
        this.textManipulator = textManipulator;
    }

    public String getJsonFromGraphqlSchema(String schema,
                                           UserDefaults defaults,
                                           List<GraphqlImplementation> graphqlImplementationList){
        this.defaults = defaults;
        this.graphqlImplementationList = graphqlImplementationList;
        List<String> schemaTypes = textManipulator.splitByBrackets(schema);
        Map<String,List<String>> schemaTypesMap = new HashMap<>();
        for (String singleSchemaType : schemaTypes) {
            List<String> typeWords = textManipulator.splitTypeToWords(singleSchemaType);
            if(!typeWords.isEmpty())
                schemaTypesMap.put(typeWords.get(1).toUpperCase(),typeWords);
        }

        return createJson("Query",schemaTypesMap).toString();
    }

    private JsonElement createJson(String currSchemaTypeName, Map<String,List<String>> schemaTypesMap) {
        boolean isTypeArray = false;
        if (currSchemaTypeName.contains("[") && currSchemaTypeName.contains("]")) {
            currSchemaTypeName = currSchemaTypeName.replace("[", "");
            currSchemaTypeName = currSchemaTypeName.replace("]", "");
            isTypeArray = true;
        }

        JsonElement json = getDefaultBasicType(currSchemaTypeName);
        if (json == null) {
            List<String> typeWords = schemaTypesMap.get(currSchemaTypeName.toUpperCase());
            json = handleCurrType(currSchemaTypeName, schemaTypesMap, typeWords);
        }

        return getJsonAsNeeded(isTypeArray, json);
    }

    private JsonElement handleCurrType(String currSchemaTypeName, Map<String, List<String>> schemaTypesMap, List<String> typeWords) {
        if (typeWords.get(0).equalsIgnoreCase("enum")) {
            return handleEnum(currSchemaTypeName, schemaTypesMap);
        }
        if (typeWords.get(0).equalsIgnoreCase("interface")) {
            return handleInterface(currSchemaTypeName, schemaTypesMap, typeWords);
        }
        if (typeWords.get(0).equalsIgnoreCase("type")) {
            return handleType(currSchemaTypeName, schemaTypesMap, typeWords);
        }
        return null;
    }

    private JsonElement getJsonAsNeeded(boolean isTypeArray, JsonElement json) {
        if (isTypeArray) {
            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < defaults.getArrayLength(); i++) {
                jsonArray.add(json);
            }
            return jsonArray;
        }
        return json;
    }

    private JsonElement handleInterface(String currSchemaType, Map<String, List<String>> schemaTypesMap, List<String> typeWords) {
        if(this.graphqlImplementationList != null) {
            for (GraphqlImplementation currGraphqlImplementation : this.graphqlImplementationList) {
                if (currSchemaType.equalsIgnoreCase(currGraphqlImplementation.getInterfaceName()) &&
                        schemaTypesMap.get(currGraphqlImplementation.getInstanceName().toUpperCase()) != null) {
                    return handleType(currGraphqlImplementation.getInstanceName(),
                            schemaTypesMap,
                            schemaTypesMap.get(currGraphqlImplementation.getInstanceName().toUpperCase()));
                }
            }
        }
        return handleType(currSchemaType, schemaTypesMap, typeWords);
    }

    private JsonElement handleType(String currSchemaType, Map<String, List<String>> schemaTypesMap, List<String> typeWords) {
        JsonObject json = new JsonObject();
        int currentWordIndex = 2;
        if(typeWords.get(currentWordIndex).equalsIgnoreCase("implements"))
            currentWordIndex = 4;
        while (currentWordIndex < typeWords.size()) {
            String currentWord = typeWords.get(currentWordIndex++);
            JsonElement innerJson = createJson(typeWords.get(currentWordIndex++), schemaTypesMap);
            json.add(currentWord, innerJson);
        }
        json.addProperty("__typename", currSchemaType);
        return json;
    }

    private JsonElement handleEnum(String currSchemaEnum, Map<String, List<String>> schemaEnumsMap) {
        if(schemaEnumsMap.get(currSchemaEnum.toUpperCase()).size() > defaults.getEnumPlace())
            return new JsonPrimitive(schemaEnumsMap.get(currSchemaEnum.toUpperCase()).get(defaults.getEnumPlace() + 1));
        return new JsonPrimitive(schemaEnumsMap.get(currSchemaEnum.toUpperCase()).get(Defaults.ENUM_PLACE + 1));
    }

    private JsonPrimitive getDefaultBasicType(String currSchemaType) {
        if(isType("Boolean",currSchemaType)){
            return new JsonPrimitive(defaults.getDefaultBoolean());
        }
        if(isType("Int",currSchemaType)){
            return new JsonPrimitive(defaults.getDefaultInteger());
        }
        if(isType("Float",currSchemaType)){
            return new JsonPrimitive(defaults.getDefaultFloat());
        }
        if(isType("String",currSchemaType)){
            return new JsonPrimitive(defaults.getDefaultString());
        }
        if(isType("Id",currSchemaType)){
            return new JsonPrimitive(defaults.getDefaultID());
        }
        return null;
    }

    private boolean isType(String type, String schemaType) {
        return schemaType.equalsIgnoreCase(type) ||
                schemaType.equalsIgnoreCase(type + "!");
    }
}
