package iaf.ofek.skypath.plugins.models;

import iaf.ofek.skypath.plugins.models.graphql.GraphqlSchema;
import lombok.Data;

@Data
public class GenerationModel {

    private String rootClassName;
    private GraphqlSchema graphqlSchema;

}
