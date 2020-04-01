package generator.controller;

import generator.api.GraphqlImplementation;
import generator.api.GraphqlToJsonWithAll;
import generator.api.UserDefaults;
import generator.graphql.GraphqlSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class JsonCreatorController {
    
    private GraphqlSchemaGenerator qraphqlSchemaGenerator;

    @Autowired
    public JsonCreatorController(GraphqlSchemaGenerator qraphqlSchemaGenerator) {
        this.qraphqlSchemaGenerator = qraphqlSchemaGenerator;
    }

    @PostMapping("/chooseImplementation/chooseDefaults/graphqlSchema2Json")
    public ResponseEntity<String> graphqlSchemaToJsonWithAll(@RequestBody GraphqlToJsonWithAll all) {
        UserDefaults defaults = all.getDefaults();
        if(all.getDefaults() == null)
            defaults = new UserDefaults();
        return graphqlSchemaToJson(all.getGraphqlSchema(),defaults,all.getGraphqlImplementations());
    }

    private ResponseEntity<String> graphqlSchemaToJson(@RequestBody String graphqlSchema,
                                                      UserDefaults defaults,
                                                      List<GraphqlImplementation> graphqlImplementation){
        try {
            String schema = qraphqlSchemaGenerator.getJsonFromGraphqlSchema(graphqlSchema,defaults,graphqlImplementation);
            return new ResponseEntity<>(schema,HttpStatus.OK);
        }catch(Exception e){
                return new ResponseEntity<>("invalid input", HttpStatus.OK);
        }
    }
}
