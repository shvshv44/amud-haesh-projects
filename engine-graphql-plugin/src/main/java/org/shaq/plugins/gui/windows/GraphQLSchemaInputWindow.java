package org.shaq.plugins.gui.windows;

import lombok.Data;

import javax.swing.*;

@Data
public class GraphQLSchemaInputWindow {
    private JTextArea graphqlInput;
    private JPanel inputPanel;
    private JScrollPane scrollPane;
    private JLabel helpText;

}
