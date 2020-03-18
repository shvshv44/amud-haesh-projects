package skypath.utils.shaqtest;

import com.google.gson.*;
import skypath.utils.shaqtest.models.JsonMessage;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonMessageParser implements JsonSerializer<JsonMessage>, JsonDeserializer<JsonMessage> {

    public JsonMessage deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    public JsonElement serialize(JsonMessage jsonMessage, Type type, JsonSerializationContext jsonSerializationContext) {

        Object pojo = jsonMessage.getPojo();
        JsonElement pojoAsElement  = jsonSerializationContext.serialize(pojo);
        JsonObject pojoAsObject = pojoAsElement.getAsJsonObject();

        Map<String,JsonElement> unknownFieldsMap = jsonMessage.getUnkownfields();
        for (Map.Entry<String, JsonElement> entry : unknownFieldsMap.entrySet()) {
            pojoAsObject.add(entry.getKey(),entry.getValue());
        }

        return pojoAsObject;
    }
}
