package org.shaq.plugins.gui.windows;

import lombok.Data;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;

import javax.swing.*;

@Data
public class GraphQLReduceSchemaWindow {

    private JLabel helpText;
    private JPanel reducePanel;
    private JPanel chooseParameters;
    private JPanel chooseOperation;
    private JPanel chooseFields;
    private JPanel mainPanel;


    public void fillSchema(GraphQLGenerationContext context) {
        // TODO: implement
    }

}
