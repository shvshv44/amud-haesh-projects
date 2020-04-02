package generator.api;

import lombok.Data;

import java.util.List;

@Data
public class GraphqlToJsonAPI {
    private String graphqlSchema;
    private List<GraphqlImplementation> graphqlImplementations;
    private UserDefaults defaults;
    private String query;

}
