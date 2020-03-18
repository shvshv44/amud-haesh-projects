package iaf.ofek.skypath.plugins.models.graphql;

import java.util.List;

public class GraphqlOperation {

    private String name;
    private GraphqlOperationType type;
    private List<GraphqlParameter> parameters;
    private GraphqlEntity returnEntity;

}
