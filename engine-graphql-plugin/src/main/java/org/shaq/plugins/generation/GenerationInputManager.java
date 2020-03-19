package org.shaq.plugins.generation;

import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import org.shaq.plugins.graphql.parser.GraphQLSchemaParser;
import org.shaq.plugins.gui.windows.GraphQLSchemaInputWindow;
import org.shaq.plugins.models.graphql.GraphQLSchema;

import java.awt.*;

public class GenerationInputManager {

    private GraphQLSchemaParser graphqlSchemaParser;
    private String inputSchema;

    public GenerationInputManager() {
        this.graphqlSchemaParser = new GraphQLSchemaParser();
    }

    public GraphQLSchema startGenerationInput() {
        inputSchema = "";
        GraphQLSchema graphqlSchema = new GraphQLSchema();

        if(showWindowAndGetInputSchema()) {
            graphqlSchema = graphqlSchemaParser.parse(inputSchema);
            showReduceGraphQLSchemaWindow(graphqlSchema);
        }


        return graphqlSchema;
    }

    private boolean showWindowAndGetInputSchema() {
        final DialogBuilder dialogBuilder = new DialogBuilder();
        final Window window = dialogBuilder.getWindow();
        GraphQLSchemaInputWindow inputWindow = new GraphQLSchemaInputWindow();

        dialogBuilder.setCenterPanel(inputWindow.getInputPanel());
        dialogBuilder.setTitle("Graphql Schema Input");
        dialogBuilder.removeAllActions();
        dialogBuilder.setOkOperation(() -> {
            inputSchema = inputWindow.getGraphqlInput().getText();
            dialogBuilder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
        });

        dialogBuilder.addOkAction().setText("Parse And Continue");
        dialogBuilder.show();

        return dialogBuilder.getDialogWrapper().getExitCode() == DialogWrapper.OK_EXIT_CODE;
    }

    private void showReduceGraphQLSchemaWindow(GraphQLSchema graphqlSchema) {

    }

}
