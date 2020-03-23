package generator.graphql;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import generator.api.GraphqlImplementation;
import generator.interfaces.db.ImplementationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphqlSchemaGenerator {

    @Autowired
    private ImplementationResolver implementationResolver;
    public String getJsonFromGraphqlSchema(String schema){
        List<String> schemaTypes = splitByBrackets(schema);
        Map<String,List<String>> schemaTypesMap = new HashMap<>();
        for (String singleSchemaType : schemaTypes) {
            List<String> typeWords = splitTypeToWords(singleSchemaType);
            schemaTypesMap.put(typeWords.get(1),typeWords);
        }

        return createJson("Query",schemaTypesMap).toString();
    }

    private JsonElement createJson(String currSchemaTypeName, Map<String,List<String>> schemaTypesMap) {
        JsonObject json = new JsonObject();
        boolean isTypeArray = false;
        if (currSchemaTypeName.contains("[") && currSchemaTypeName.contains("]")) {
            currSchemaTypeName = currSchemaTypeName.replace("[", "");
            currSchemaTypeName = currSchemaTypeName.replace("]", "");
            isTypeArray = true;
        }

        JsonPrimitive defaultType = getDefaultBasicType(currSchemaTypeName);
        if (defaultType != null) {
            return getJsonAsNeeded(isTypeArray, defaultType);
        }

        List<String> typeWords = schemaTypesMap.get(currSchemaTypeName);
        if(typeWords.get(0).equalsIgnoreCase("enum")) {
            return handleEnum(currSchemaTypeName, schemaTypesMap);
        }
        if(typeWords.get(0).equalsIgnoreCase("interface")){
            handleInterface(currSchemaTypeName, schemaTypesMap, json, typeWords);
        }
        if(typeWords.get(0).equalsIgnoreCase("type")){
            handleType(currSchemaTypeName, schemaTypesMap, json, typeWords);
        }
        return getJsonAsNeeded(isTypeArray, json);
    }

    private void handleInterface(String currSchemaType, Map<String, List<String>> schemaTypesMap, JsonObject json, List<String> typeWords) {
        boolean isImplementationFound = false;
        List<GraphqlImplementation> graphqlImplementationList = implementationResolver.findAll();
        for (GraphqlImplementation currGraphqlImplementation : graphqlImplementationList) {
            if(currSchemaType.equalsIgnoreCase(currGraphqlImplementation.getInterfaceName()) &&
                    schemaTypesMap.get(currGraphqlImplementation.getImplementationName())!= null) {
                isImplementationFound = true;
                handleType( currGraphqlImplementation.getImplementationName(),
                            schemaTypesMap,
                            json,
                            schemaTypesMap.get(currGraphqlImplementation.getImplementationName()));
                break;
            }
        }
        if (!isImplementationFound)
            handleType(currSchemaType, schemaTypesMap, json, typeWords);
    }

    private void handleType(String currSchemaType, Map<String, List<String>> schemaTypesMap, JsonObject json, List<String> typeWords) {
        int currentWordIndex = 2;
        if(typeWords.get(currentWordIndex).equalsIgnoreCase("implements"))
            currentWordIndex = 4;
        while (currentWordIndex < typeWords.size()) {
                String currentWord = typeWords.get(currentWordIndex++);
            JsonElement innerJson = createJson(typeWords.get(currentWordIndex++), schemaTypesMap);
            json.add(currentWord, innerJson);
        }
        json.addProperty("__typename", currSchemaType);
    }

    private JsonElement handleEnum(String currSchemaEnum, Map<String, List<String>> schemaEnumsMap) {
        return new JsonPrimitive(schemaEnumsMap.get(currSchemaEnum).get(Defaults.ENUM_PLACE + 1));
    }

    private JsonElement getJsonAsNeeded(boolean isTypeArray, JsonElement json) {
        if (isTypeArray) {
            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < Defaults.ARRAY_LENGTH; i++) {
                jsonArray.add(json);
            }
            return jsonArray;
        }
        return json;
    }

    private JsonPrimitive getDefaultBasicType(String currSchemaType) {
        if(isBooleanType(currSchemaType)){
            return new JsonPrimitive(Defaults.BOOLEAN);
        }
        if(isIntType(currSchemaType)){
            return new JsonPrimitive(Defaults.INTEGER);
        }
        if(isFloatType(currSchemaType)){
            return new JsonPrimitive(Defaults.FLOAT);
        }
        if(isStringType(currSchemaType)){
            return new JsonPrimitive(Defaults.STRING);
        }
        if(isIdType(currSchemaType)){
            return new JsonPrimitive(Defaults.ID);
        }
        return null;
    }

    private boolean isBooleanType(String schemaType) {
        return  schemaType.equalsIgnoreCase("Boolean") ||
                schemaType.equalsIgnoreCase("Boolean!");
    }

    private boolean isIntType(String schemaType) {
        return  schemaType.equalsIgnoreCase("Int") ||
                schemaType.equalsIgnoreCase("Int!");
    }

    private boolean isFloatType(String schemaType) {
        return  schemaType.equalsIgnoreCase("Float") ||
                schemaType.equalsIgnoreCase("Float!");
    }

    private boolean isStringType(String schemaType) {
        return  schemaType.equalsIgnoreCase("String") ||
                schemaType.equalsIgnoreCase("String!");
    }

    private boolean isIdType(String schemaType) {
        return  schemaType.equalsIgnoreCase("ID") ||
                schemaType.equalsIgnoreCase("ID!");
    }

    public List<String> splitTypeToWords(String schema) {
        List<String> schemaTypes = new ArrayList<>();
        for (String type : schema.split("[\\s:{}] ?",-1)) {
            schemaTypes.add(type);
        }
        return removeEmptyStrings(schemaTypes);
    }

    public List<String> splitByBrackets(String schema) {
        List<String> schemaTypes = new ArrayList<>();
        for (String type : schema.split("}")) {
            schemaTypes.add(type);
        }
        return removeEmptyStrings(schemaTypes);
    }

    private static List<String> removeEmptyStrings(Iterable<String> splited) {
        List<String> noEmptyStrings = new ArrayList<>();
        for (String curr : splited) {
            if(!curr.equals(""))
                noEmptyStrings.add(curr);
        }
        return noEmptyStrings;
    }
}
