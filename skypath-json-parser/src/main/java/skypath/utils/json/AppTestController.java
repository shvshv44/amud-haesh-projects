package skypath.utils.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppTestController {


    @RequestMapping
    @ResponseBody
    public String test(@RequestBody String someJson) {


        return "";
    }


}
