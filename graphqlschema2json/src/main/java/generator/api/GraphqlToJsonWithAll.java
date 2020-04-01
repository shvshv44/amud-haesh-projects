package generator.api;

import lombok.Data;

import java.util.List;

@Data
public class GraphqlToJsonWithAll {
    private String graphqlSchema;
    private List<GraphqlImplementation> graphqlImplementations;
    private UserDefaults defaults;
}
