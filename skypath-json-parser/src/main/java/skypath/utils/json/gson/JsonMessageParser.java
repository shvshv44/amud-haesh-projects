package skypath.utils.json.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.json.*;
import skypath.utils.json.pojos.JsonMessage;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonMessageParser implements JsonSerializer<JsonMessage>, JsonDeserializer<JsonMessage> {

    public JsonMessage deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JSONObject jsonObject = new JSONObject(jsonElement.toString());
        JsonMessage jsonMessage = new JsonMessage();
        try {
            Class<?>  pojoClass = (Class<?>) (((ParameterizedType) type).getActualTypeArguments())[0];
            Constructor<?> cons = pojoClass.getConstructor();
            Object pojo = cons.newInstance();
            jsonMessage.setPojo(pojo);
            Field[] fields = pojoClass.getDeclaredFields();
            Method[] allMethods = pojoClass.getMethods();
            List<Method> setters = getAllSetters(allMethods);
            for (Method currSetter: setters) {
              handleField(jsonObject, fields, currSetter, pojo);
            }
            setUnknownFields(jsonObject, jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonMessage;
    }

    public JsonElement serialize(JsonMessage jsonMessage, Type type, JsonSerializationContext jsonSerializationContext) {

        Object pojo = jsonMessage.getPojo();
        JsonElement pojoAsElement  = jsonSerializationContext.serialize(pojo);
        JsonObject pojoAsObject = pojoAsElement.getAsJsonObject();

        Map<String,JsonElement> unknownFieldsMap = jsonMessage.getUnknownFields();

        try {
            for (Map.Entry<String, JsonElement> entry : unknownFieldsMap.entrySet()) {
                pojoAsObject.add(entry.getKey(), entry.getValue());
            }
        } catch (NullPointerException e){
            System.out.println("no unknown fields");
        }

        return pojoAsObject;
    }

    private List<Method> getAllSetters(Method[]  allMethods){
        List<Method> setters = new ArrayList<>();
        for(Method method : allMethods) {
            if(method.getName().startsWith("set")) {
                setters.add(method);
            }
        }
        return setters;
    }

    private void handleField(JSONObject jsonObject, Field [] fields, Method setter, Object pojo) throws InvocationTargetException, IllegalAccessException {
        for(int i = 0; i < fields.length; i++) {
            if(fields[i].getName().equalsIgnoreCase(setter.getName().substring(3))) {
                setter.setAccessible(true);
                if(jsonObject.get(fields[i].getName()) instanceof JSONArray){
                    setter.invoke(pojo, jsonObject.getJSONArray(fields[i].getName()).toList());
                } else if(jsonObject.get(fields[i].getName()) instanceof JSONObject) {
                    JsonParser jsonParser = new JsonParser();
                    JsonObject gsonObject = (JsonObject)jsonParser.parse(jsonObject.get(fields[i].getName()).toString());
                    setter.invoke(pojo, gsonObject);
                } else {
                    setter.invoke(pojo, jsonObject.get(fields[i].getName()));
                }
                jsonObject.remove(fields[i].getName());
            }
        }
    }

    private void setUnknownFields(JSONObject jsonObject, JsonMessage jsonMessage){
        Map<String, JsonElement> unknownFields = new Gson().fromJson(
                jsonObject.toString(), new TypeToken<HashMap<String, JsonElement>>() {}.getType()
        );
        jsonMessage.setUnknownFields(unknownFields);
    }
}
