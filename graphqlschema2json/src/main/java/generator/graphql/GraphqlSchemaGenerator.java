package generator.graphql;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphqlSchemaGenerator {

    public String getJsonFromGraphqlSchema(String schema){
        List<String> schemaTypes = splitByType(schema);
        Map<String,List<String>> schemaTypesMap = new HashMap<>();
        for (String singleSchemaType : schemaTypes) {
            List<String> typeWords = splitTypeToWords(singleSchemaType);
            schemaTypesMap.put(typeWords.get(0),typeWords);
        }

        return createjson("Query",schemaTypesMap).toString();
    }

    private JsonElement createjson(String currSchemaType, Map<String,List<String>> schemaTypesMap) {
        JsonPrimitive defaultType = getDefaultBasicType(currSchemaType);
        if(defaultType != null){
            return defaultType;
        }
        List<String> typeWords = schemaTypesMap.get(currSchemaType);
        JsonObject json = new JsonObject();
        int currentWordIndex = 1;
        while (currentWordIndex < typeWords.size()){
            String currentWord = typeWords.get(currentWordIndex++);
            json.add(currentWord, createjson(typeWords.get(currentWordIndex++), schemaTypesMap));
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

    public List<String> splitByType(String schema) {
        List<String> schemaTypes = new ArrayList<>();
        for (String type : schema.split("type")) {
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
