package generator.graphql;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import generator.api.GraphqlImplementation;
import generator.api.GraphqlToJsonAPI;
import generator.api.UserDefaults;
import generator.graphql.manipulators.GraphqlSchemaTextManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GraphqlSchemaGenerator {

    public GraphqlSchemaTextManipulator textManipulator;
    private UserDefaults defaults;
    private List<GraphqlImplementation> graphqlImplementationList;
    private String query;

    @Autowired
    public GraphqlSchemaGenerator(GraphqlSchemaTextManipulator textManipulator) {
        this.textManipulator = textManipulator;
    }

    private void initGraphqlToJson(GraphqlToJsonAPI graphqlToJson) {
        this.defaults = graphqlToJson.getDefaults();
        this.graphqlImplementationList = graphqlToJson.getGraphqlImplementations();
        this.query = graphqlToJson.getQuery();
    }

    public String getJsonFromGraphqlSchema(GraphqlToJsonAPI graphqlToJson){
        initGraphqlToJson(graphqlToJson);

        List<String> schemaTypes = textManipulator.splitByBrackets(graphqlToJson.getGraphqlSchema());
        Map<String,List<String>> schemaTypesMap = new HashMap<>();
        for (String singleSchemaType : schemaTypes) {
            List<String> typeWords = textManipulator.splitTypeToWords(singleSchemaType);
            if(!typeWords.isEmpty())
                schemaTypesMap.put(typeWords.get(1),typeWords);
        }

        JsonObject json = new JsonObject();
        json.add("data",createJson("Query","",schemaTypesMap));
        return json.toString();
    }

    private  List<String> getCurrTypeWords(String query, String currObj) {
        if(query==null)
            return new ArrayList<>();
        String notPrettyQuery = query.replaceAll("... on ","on-");
        notPrettyQuery = notPrettyQuery.replaceAll("\\s"," ");
        notPrettyQuery = notPrettyQuery.replaceAll("\\(.*?\\)","");
        return textManipulator.splitQueryToSubWords(notPrettyQuery,currObj);
    }

    private JsonElement createJson(String currSchemaTypeName, String currWord, Map<String, List<String>> schemaTypesMap) {
        boolean isTypeArray = false;
        if (currSchemaTypeName.contains("[") && currSchemaTypeName.contains("]")) {
            currSchemaTypeName = currSchemaTypeName.replace("[", "");
            currSchemaTypeName = currSchemaTypeName.replace("]", "");
            isTypeArray = true;
        }

        JsonElement json = getDefaultBasicType(currSchemaTypeName);
        if (json == null) {
            List<String> typeWords = schemaTypesMap.get(currSchemaTypeName);
            json = handleCurrType(currSchemaTypeName, currWord, schemaTypesMap, typeWords);
        }

            return getJsonAsNeeded(isTypeArray, json);
    }

    private JsonElement handleCurrType(String currSchemaTypeName, String currWord, Map<String, List<String>> schemaTypesMap, List<String> typeWords) {
        if (typeWords.get(0).equalsIgnoreCase("enum")) {
            return handleEnum(currSchemaTypeName, schemaTypesMap);
        }
        if (typeWords.get(0).equalsIgnoreCase("interface")) {
            return handleInterface(currSchemaTypeName,currWord, schemaTypesMap, typeWords);
        }
        if (typeWords.get(0).equalsIgnoreCase("type")) {
            return handleType(currSchemaTypeName, currWord, schemaTypesMap, typeWords);
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

    private JsonElement handleInterface(String currSchemaType, String currTypeKey, Map<String, List<String>> schemaTypesMap, List<String> typeWords) {
        List<String> queryWordsList = getCurrTypeWords(this.query,currTypeKey);
        List<String> interfacesWordsList = queryWordsList.stream().filter(str -> str.contains("on-")).collect(Collectors.toList());
            interfacesWordsList.replaceAll(str -> {
                if (str.contains("on-"))
                    return str.replaceFirst("on-", "");
                return str;
            });
        if(this.graphqlImplementationList != null){
            for (GraphqlImplementation currGraphqlImplementation : this.graphqlImplementationList) {
                if (currSchemaType.equalsIgnoreCase(currGraphqlImplementation.getInterfaceName()) &&
                        schemaTypesMap.get(currGraphqlImplementation.getInstanceName()) != null) {
                    return handleType(currGraphqlImplementation.getInstanceName(),
                            currTypeKey,
                            schemaTypesMap,
                            schemaTypesMap.get(currGraphqlImplementation.getInstanceName()));
                }
            }
        }
        for (String currInstance : interfacesWordsList) {
            List<String> currInsanceTypes = schemaTypesMap.get(currInstance);
            if(currInsanceTypes != null && currInsanceTypes.get(3).equals(currSchemaType)) {
                JsonObject implJson = handleType(currInstance,
                        "on-" + currInstance,
                        schemaTypesMap,
                        schemaTypesMap.get(currInstance)).getAsJsonObject();
                JsonObject currJson = handleType(currSchemaType, currTypeKey, schemaTypesMap, typeWords).getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : implJson.entrySet()) {
                    currJson.add(entry.getKey(), entry.getValue());
                }
                return currJson;
            }

        }
        return handleType(currSchemaType, currTypeKey, schemaTypesMap, typeWords);
    }

    private JsonElement handleType(String currSchemaType,String currTypeKey, Map<String, List<String>> schemaTypesMap, List<String> typeWords) {
        List<String> queryWordsList = getCurrTypeWords(this.query,currTypeKey);
        JsonObject json = new JsonObject();
        int currentWordIndex = 2;
        if(typeWords.get(currentWordIndex).equalsIgnoreCase("implements"))
            currentWordIndex = 4;
        while (currentWordIndex < typeWords.size()) {
            String currentWord = typeWords.get(currentWordIndex++);
            if(queryWordsList.contains(currentWord) || queryWordsList.isEmpty() || currSchemaType.equalsIgnoreCase("query")) {
                JsonElement innerJson = createJson(typeWords.get(currentWordIndex++), currentWord, schemaTypesMap);
                json.add(currentWord, innerJson);
            }else{
                currentWordIndex++;
            }
        }
        if(queryWordsList.contains("__typename") || (queryWordsList.isEmpty() && !currSchemaType.equals("Query")))
            json.addProperty("__typename", currSchemaType);
        return json;
    }

    private JsonElement handleEnum(String currSchemaEnum, Map<String, List<String>> schemaEnumsMap) {
        if(schemaEnumsMap.get(currSchemaEnum).size() > defaults.getEnumPlace())
            return new JsonPrimitive(schemaEnumsMap.get(currSchemaEnum).get(defaults.getEnumPlace() + 1));
        return new JsonPrimitive(schemaEnumsMap.get(currSchemaEnum).get(Defaults.ENUM_PLACE + 1));
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
