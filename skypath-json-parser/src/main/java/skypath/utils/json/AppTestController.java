package skypath.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import skypath.utils.json.gson.JsonMessageParser;
import skypath.utils.json.pojos.JsonMessage;
import skypath.utils.json.pojos.SimplePojo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AppTestController {
    private Gson gson;

    public AppTestController() {
        this.gson = new GsonBuilder().registerTypeAdapter(JsonMessage.class,new JsonMessageParser()).create();
    }

    @ResponseBody
    @GetMapping("/ser")
    public String serTest() {

        SimplePojo pojo = new SimplePojo();
        pojo.setX(1);
        pojo.setY(2);

        Map<String, JsonElement> other = new HashMap<String, JsonElement>();
        other.put("z", new JsonPrimitive(3));

        JsonMessage<SimplePojo> jsonMessage = new JsonMessage<SimplePojo>();
        jsonMessage.setPojo(pojo);
        jsonMessage.setUnknownFields(other);

        String result = gson.toJson(jsonMessage);
        return result;
    }

    @ResponseBody
    @PostMapping("/des")
    public String serTest(@RequestBody String json) {
        Type pojoType = new TypeToken<JsonMessage<SimplePojo>>() {}.getType();
        JsonMessage result = gson.fromJson(json, pojoType);
        return gson.toJson(result);
    }
}
