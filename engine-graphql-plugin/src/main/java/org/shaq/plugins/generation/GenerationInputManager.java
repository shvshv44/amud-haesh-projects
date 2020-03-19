package org.shaq.plugins.generation;

import com.intellij.openapi.ui.DialogBuilder;
import org.shaq.plugins.graphql.parser.GraphqlSchemaParser;
import org.shaq.plugins.gui.GraphqlSchemaInputWindow;
import org.shaq.plugins.models.graphql.GraphqlSchema;

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
