package iaf.ofek.skypath.plugins.models.graphql;

import iaf.ofek.skypath.plugins.models.graphql.enums.GraphqlOperationType;
import lombok.Data;

import java.util.List;


@Data
public class GraphqlOperation {

    private String name;
    private GraphqlOperationType type;
    private List<GraphqlParameter> parameters;
    private GraphqlEntity returnEntity;

}
