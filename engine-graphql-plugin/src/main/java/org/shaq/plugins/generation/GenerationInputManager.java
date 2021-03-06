package org.shaq.plugins.generation;

import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.shaq.plugins.gui.windows.GraphQLReduceSchemaWindow;
import org.shaq.plugins.gui.windows.GraphQLSchemaInputWindow;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;
import org.shaq.plugins.models.user.UserChoiceGraphQLContext;

import java.awt.*;

public class GenerationInputManager {

    private GraphQLGenerationContextAdapter adapter;
    private SchemaParser schemaParser;
    private String inputSchema;
    private UserChoiceGraphQLContext userChoices;

    public GenerationInputManager(GraphQLGenerationContextAdapter adapter, SchemaParser schemaParser) {
        this.adapter = adapter;
        this.schemaParser = schemaParser;
    }

    public UserChoiceGraphQLContext startGenerationInput() {
        inputSchema = "";
        userChoices = null;

        if(showWindowAndGetInputSchema()) {
            TypeDefinitionRegistry graphqlSchema = schemaParser.parse(inputSchema);
            GraphQLGenerationContext context = adapter.adapt(graphqlSchema);
            showReduceGraphQLSchemaWindow(context);
        }

        return userChoices;
    }

    private boolean showWindowAndGetInputSchema() {
        final DialogBuilder dialogBuilder = new DialogBuilder();
        final Window window = dialogBuilder.getWindow();
        GraphQLSchemaInputWindow inputWindow = new GraphQLSchemaInputWindow();

        dialogBuilder.setCenterPanel(inputWindow.getInputPanel());
        dialogBuilder.setTitle("GraphQL Schema - Input");
        dialogBuilder.removeAllActions();
        dialogBuilder.setOkOperation(() -> {
            inputSchema = inputWindow.getGraphqlInput().getText();
            dialogBuilder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
        });

        dialogBuilder.addOkAction().setText("Parse And Continue");
        dialogBuilder.show();

        return dialogBuilder.getDialogWrapper().getExitCode() == DialogWrapper.OK_EXIT_CODE;
    }

    private void showReduceGraphQLSchemaWindow(GraphQLGenerationContext generationContext) {
        final DialogBuilder dialogBuilder = new DialogBuilder();
        final Window window = dialogBuilder.getWindow();
        GraphQLReduceSchemaWindow inputWindow = new GraphQLReduceSchemaWindow();
        inputWindow.fillSchema(generationContext);

        dialogBuilder.setCenterPanel(inputWindow.getMainPanel());
        dialogBuilder.setTitle("GraphQL Schema - Reduce Fields");
        dialogBuilder.removeAllActions();
        dialogBuilder.setOkOperation(() -> {
            userChoices = inputWindow.getUserChoiceContext();
            dialogBuilder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
        });

        dialogBuilder.addOkAction().setText("Generate Classes");
        dialogBuilder.show();
    }

}
