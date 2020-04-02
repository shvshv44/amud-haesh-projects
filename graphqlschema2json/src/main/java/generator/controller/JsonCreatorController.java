package generator.controller;

import generator.api.GraphqlToJsonAPI;
import generator.api.UserDefaults;
import generator.graphql.GraphqlSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JsonCreatorController {
    
    private GraphqlSchemaGenerator qraphqlSchemaGenerator;

    @Autowired
    public JsonCreatorController(GraphqlSchemaGenerator qraphqlSchemaGenerator) {
        this.qraphqlSchemaGenerator = qraphqlSchemaGenerator;
    }

    @PostMapping("/graphqlSchema2Json")
    public ResponseEntity<String> graphqlSchemaToJsonWithAll(@RequestBody GraphqlToJsonAPI graphqlToJson) {
        if(graphqlToJson.getDefaults() == null)
            graphqlToJson.setDefaults(new UserDefaults());
        return graphqlSchemaToJson(graphqlToJson);
    }

    private ResponseEntity<String> graphqlSchemaToJson(GraphqlToJsonAPI graphqlToJson){
        try {
            String schema = qraphqlSchemaGenerator.getJsonFromGraphqlSchema(graphqlToJson);
            return new ResponseEntity<>(schema,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
