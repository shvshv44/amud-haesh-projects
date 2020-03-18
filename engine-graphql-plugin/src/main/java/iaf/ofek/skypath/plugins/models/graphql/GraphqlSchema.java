package iaf.ofek.skypath.plugins.models.graphql;

import lombok.Data;

import java.util.List;


@Data
public class GraphqlSchema {

    private List <GraphqlOperation> operations;

}
