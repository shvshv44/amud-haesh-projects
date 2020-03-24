package generator.graphql;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import generator.api.GraphqlImplementation;
import generator.api.UserDefaults;
import generator.graphql.manipulators.GraphqlSchemaTextManipulator;
import generator.interfaces.db.GraphqlImplementationResolver;
import generator.interfaces.db.UserDefaultResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphqlSchemaGenerator {

    public GraphqlSchemaTextManipulator textManipulator;
    private GraphqlImplementationResolver graphqlImplementationResolver;
    private UserDefaultResolver userDefaultsResolver;
    private UserDefaults defaults;

    @Autowired
    public GraphqlSchemaGenerator(GraphqlSchemaTextManipulator textManipulator,
                                  GraphqlImplementationResolver graphqlImplementationResolver,
                                  UserDefaultResolver userDefaultsResolver) {
        this.textManipulator = textManipulator;
        this.graphqlImplementationResolver = graphqlImplementationResolver;
        this.userDefaultsResolver = userDefaultsResolver;
    }

    public String getJsonFromGraphqlSchema(String schema){
        this.defaults = createUserDefaults();
        List<String> schemaTypes = textManipulator.splitByBrackets(schema);
        Map<String,List<String>> schemaTypesMap = new HashMap<>();
        for (String singleSchemaType : schemaTypes) {
            List<String> typeWords = textManipulator.splitTypeToWords(singleSchemaType);
            schemaTypesMap.put(typeWords.get(1),typeWords);
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
            List<String> typeWords = schemaTypesMap.get(currSchemaTypeName);
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
        List<GraphqlImplementation> graphqlImplementationList = graphqlImplementationResolver.findAll();
        for (GraphqlImplementation currGraphqlImplementation : graphqlImplementationList) {
            if(currSchemaType.equalsIgnoreCase(currGraphqlImplementation.getInterfaceName()) &&
                    schemaTypesMap.get(currGraphqlImplementation.getImplementationName())!= null) {
                return handleType( currGraphqlImplementation.getImplementationName(),
                        schemaTypesMap,
                        schemaTypesMap.get(currGraphqlImplementation.getImplementationName()));
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

    private UserDefaults createUserDefaults() {
        List<UserDefaults> userDefaultsList = userDefaultsResolver.findAll();
        if(!userDefaultsList.isEmpty()) {
            UserDefaults userDefaults = userDefaultsList.get(userDefaultsList.size() - 1);
            if (userDefaults != null)
                return userDefaults;
        }
        return new UserDefaults();
    }
}
