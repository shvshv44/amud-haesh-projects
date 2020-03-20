package org.shaq.plugins.generation;

import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.shaq.plugins.gui.windows.GraphQLSchemaInputWindow;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;

import java.awt.*;

public class GenerationInputManager {

    private GraphQLGenerationContextAdapter adapter;
    private SchemaParser schemaParser;
    private String inputSchema;

    public GenerationInputManager(GraphQLGenerationContextAdapter adapter, SchemaParser schemaParser) {
        this.adapter = adapter;
        this.schemaParser = schemaParser;
    }

    public TypeDefinitionRegistry startGenerationInput() {
        inputSchema = "";

        if(showWindowAndGetInputSchema()) {
            TypeDefinitionRegistry graphqlSchema = schemaParser.parse(inputSchema);
            GraphQLGenerationContext context = adapter.adapt(graphqlSchema);
            showReduceGraphQLSchemaWindow(context);
        }


        return new TypeDefinitionRegistry();
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

    private void showReduceGraphQLSchemaWindow(GraphQLGenerationContext generationContext) {
        System.out.println("shaq"); //TODO: implement
    }

}
