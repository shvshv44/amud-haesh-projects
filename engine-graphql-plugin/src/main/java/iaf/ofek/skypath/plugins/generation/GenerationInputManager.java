package iaf.ofek.skypath.plugins.generation;

import com.intellij.openapi.ui.DialogBuilder;
import iaf.ofek.skypath.plugins.graphql.parser.GraphqlSchemaParser;
import iaf.ofek.skypath.plugins.gui.GraphqlSchemaInputWindow;
import iaf.ofek.skypath.plugins.models.graphql.GraphqlSchema;

import java.awt.*;

public class GenerationInputManager {

    private GraphqlSchemaParser graphqlSchemaParser;

    public GraphqlSchema startGenerationInput() {

        showInputWindow();

        return graphqlSchemaParser.parse("TODO");
    }

    private Window showInputWindow() {
        final DialogBuilder dialogBuilder = new DialogBuilder();
        final Window window = dialogBuilder.getWindow();
        GraphqlSchemaInputWindow inputWindow = new GraphqlSchemaInputWindow();
        dialogBuilder.setCenterPanel(inputWindow.getInputPanel());
        dialogBuilder.setTitle("Graphql Schema Input");
        dialogBuilder.removeAllActions();
        dialogBuilder.show();

        return window;
    }


}
