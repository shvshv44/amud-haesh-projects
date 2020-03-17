package skypath.utils.json.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import skypath.utils.json.pojos.Student;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDeserializer implements JsonDeserializer<Student> {

    @Override
    public Student deserialize(JsonElement elem, Type type,
                               JsonDeserializationContext context) {
        JsonObject object = elem.getAsJsonObject();
        Map<String, String> map = object.entrySet()
                .stream()
                .filter(e -> e.getValue().isJsonPrimitive())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue().getAsJsonPrimitive().toString()));
        String id = map.get("id");
        String name = map.get("name");
        map.remove("id");
        map.remove("name");
        return new Student(id, name, map);
    }
}