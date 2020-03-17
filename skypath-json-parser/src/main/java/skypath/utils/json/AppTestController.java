package skypath.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skypath.utils.json.gson.StudentDeserializer;
import skypath.utils.json.pojos.Student;

import java.lang.reflect.Type;
import java.util.Map;

@Controller
public class AppTestController {
    @RequestMapping
    @ResponseBody
    @PostMapping("/test")
    public String test(@RequestBody String someJson) {
        Type type = new TypeToken<Map<String, Student>>(){}.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(type, new StudentDeserializer())
                .create();
        Student mappedFields = gson.fromJson(someJson, type);
        return gson.toJson(mappedFields);
    }
}
