package generator.controller;

import generator.api.GraphqlImplementation;
import generator.graphql.GraphqlSchemaGenerator;
import generator.interfaces.db.ImplementationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonCreatorController {

    @Autowired
    private GraphqlSchemaGenerator qraphqlSchemaGenerator;
    @Autowired
    private ImplementationResolver implementationResolver;

    @ResponseBody
    @PostMapping("/graphqlSchema2Json")
    public String graphqlSchemaToJson(@RequestBody String graphqlSchema){
        return qraphqlSchemaGenerator.getJsonFromGraphqlSchema(graphqlSchema);
    }

    @ResponseBody
    @PostMapping("/chooseImplementation")
    public String chooseImplementation(@RequestBody GraphqlImplementation graphqlImplementation){
        return "saved successfully " + implementationResolver.save(graphqlImplementation);
    }
}
