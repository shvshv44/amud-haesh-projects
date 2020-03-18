package iaf.ofek.skypath.plugins.gui;

import lombok.Data;

import javax.swing.*;

@Data
public class GraphqlSchemaInputWindow {
    private JTextArea graphqlInput;
    private JPanel inputPanel;
    private JButton parseBtn;
    private JScrollPane scrollPane;
    private JLabel helpText;

}
