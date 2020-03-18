package skypath.utils.shaqtest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skypath.utils.shaqtest.models.JsonMessage;
import skypath.utils.shaqtest.models.SimplePojo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ShaqTestController {

    private Gson gson;

    public ShaqTestController() {
        //Type pojoType = new TypeToken<JsonMessage<SimplePojo>>() {}.getType();
        this.gson = new GsonBuilder().registerTypeAdapter(JsonMessage.class,new JsonMessageParser()).create();
    }

    @GetMapping("/shaq/ser")
    @ResponseBody
    public String serTest() {

        SimplePojo pojo = new SimplePojo();
        pojo.setX(1);
        pojo.setY(2);

        Map<String, JsonElement> other = new HashMap<String, JsonElement>();
        other.put("z", new JsonPrimitive(3));

        JsonMessage<SimplePojo> jsonMessage = new JsonMessage<SimplePojo>();
        jsonMessage.setPojo(pojo);
        jsonMessage.setUnkownfields(other);

        String result = gson.toJson(jsonMessage);
        return result;

    }


}
