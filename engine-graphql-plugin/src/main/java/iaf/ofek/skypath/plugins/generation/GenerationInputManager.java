package iaf.ofek.skypath.plugins.generation;

import com.intellij.openapi.ui.DialogBuilder;
import iaf.ofek.skypath.plugins.graphql.parser.GraphqlSchemaParser;
import iaf.ofek.skypath.plugins.gui.GraphqlSchemaInputWindow;
import iaf.ofek.skypath.plugins.models.graphql.GraphqlSchema;

import java.awt.*;

public class GenerationInputManager {

    private GraphqlSchemaParser graphqlSchemaParser;

    public GraphqlSchema startGenerationInput() {
        final DialogBuilder dialogBuilder = new DialogBuilder();
        final Window window = dialogBuilder.getWindow();
        window.setSize(500,500);
        GraphqlSchemaInputWindow inputWindow = new GraphqlSchemaInputWindow();
        dialogBuilder.setCenterPanel(inputWindow.getInputPanel());
        dialogBuilder.setTitle("Graphql Schema Input");
        dialogBuilder.removeAllActions();
        dialogBuilder.show();


        return graphqlSchemaParser.parse("TODO");
    }


}
