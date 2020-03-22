package generator.controller;

import com.google.gson.Gson;
import generator.graphql.GraphqlSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonCreatorController {

    @Autowired
    private GraphqlSchemaGenerator qraphqlSchemaGenerator;

    @PostMapping("/graphqlSchema2Json")
    @ResponseBody
    public String graphqlSchemaToJson(@RequestBody String graphqlSchema){
        return qraphqlSchemaGenerator.getJsonFromGraphqlSchema(graphqlSchema);
    }
}
