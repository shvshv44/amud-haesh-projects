package generator.controller;

import generator.api.GraphqlImplementation;
import generator.api.UserDefaults;
import generator.graphql.GraphqlSchemaGenerator;
import generator.interfaces.db.GraphqlImplementationResolver;
import generator.interfaces.db.UserDefaultResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonCreatorController {
    
    private GraphqlSchemaGenerator qraphqlSchemaGenerator;
    private GraphqlImplementationResolver graphqlImplementationResolver;
    private UserDefaultResolver userDefaultsResolver;

    @Autowired
    public JsonCreatorController(GraphqlSchemaGenerator qraphqlSchemaGenerator,
                                 GraphqlImplementationResolver graphqlImplementationResolver,
                                 UserDefaultResolver userDefaultsResolver) {
        this.qraphqlSchemaGenerator = qraphqlSchemaGenerator;
        this.graphqlImplementationResolver = graphqlImplementationResolver;
        this.userDefaultsResolver = userDefaultsResolver;
    }

    @ResponseBody
    @PostMapping("/graphqlSchema2Json")
    public String graphqlSchemaToJson(@RequestBody String graphqlSchema){
        return qraphqlSchemaGenerator.getJsonFromGraphqlSchema(graphqlSchema);
    }

    @ResponseBody
    @PostMapping("/chooseImplementation")
    public String chooseImplementation(@RequestBody GraphqlImplementation graphqlImplementation){
        return "saved successfully " + graphqlImplementationResolver.save(graphqlImplementation);
    }
    
    @ResponseBody
    @PostMapping("/chooseDefaults")
    public String chooseUserDefaults(@RequestBody UserDefaults userDefaults){
        return "saved successfully " + userDefaultsResolver.save(userDefaults);
    }

    @ResponseBody
    @DeleteMapping("/resetDefaults")
    public String resetUserDefaults(){
        userDefaultsResolver.deleteAll();
        return "deleted defaults successfully";
    }

    @ResponseBody
    @DeleteMapping("/resetImplementation")
    public String resetImplementation(){
        graphqlImplementationResolver.deleteAll();
        return "deleted implementations successfully";
    }
}
