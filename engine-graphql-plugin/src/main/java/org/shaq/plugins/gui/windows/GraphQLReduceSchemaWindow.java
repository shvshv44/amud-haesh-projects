package org.shaq.plugins.gui.windows;

import lombok.Data;

import javax.swing.*;

@Data
public class GraphQLReduceSchemaWindow {
    private JLabel helpText;
    private JPanel reducePanel;
    private JPanel chooseParameters;
    private JPanel chooseOperation;
    private JPanel chooseFields;
}
