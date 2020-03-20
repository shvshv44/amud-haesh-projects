package org.shaq.plugins.generation;

import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.shaq.plugins.gui.windows.GraphQLSchemaInputWindow;

import java.awt.*;

public class GenerationInputManager {

    private SchemaParser schemaParser;
    private String inputSchema;

    public GenerationInputManager() {
        this.schemaParser = new SchemaParser();
    }

    public TypeDefinitionRegistry startGenerationInput() {
        inputSchema = "";

        if(showWindowAndGetInputSchema()) {
            TypeDefinitionRegistry graphqlSchema = schemaParser.parse(inputSchema);
            //TODO: should implement a adapter to GraphQLGenerationContext
            showReduceGraphQLSchemaWindow(graphqlSchema);
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

    private void showReduceGraphQLSchemaWindow(TypeDefinitionRegistry graphqlSchema) {
        System.out.println("shaq"); //TODO: implement
    }

}
