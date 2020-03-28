package org.shaq.plugins.gui.components;

import org.shaq.plugins.models.graphql.GraphQLFieldType;
import org.shaq.plugins.models.graphql.GraphQLParameter;

import javax.swing.*;
import java.awt.*;

public class ParameterCheckBoxPanel extends JPanel {

    private GraphQLParameter parameter;
    private JCheckBox checkBox;
    private JLabel text;

    public ParameterCheckBoxPanel(GraphQLParameter parameter) {
        super();
        this.parameter = parameter;
        checkBox = new JCheckBox();
        text = new JLabel();
        text.setText(parameter.getName() + " : " + graphQLTypeToString(parameter.getType()));
        setLayout(new BorderLayout());
        add(checkBox, BorderLayout.WEST);
        add(text, BorderLayout.CENTER);
        setOpaque(false);

        if(!parameter.getType().getIsNullable()) {
            checkBox.setSelected(true);
            checkBox.setEnabled(false);
        }
    }


    private String graphQLTypeToString(GraphQLFieldType type) {
        String typeAsString = "";
        if (type.getIsCollection()) typeAsString += "[";
        typeAsString += type.getCoreType().getName();
        if (type.getIsCollection()) typeAsString += "]";
        if (type.getIsNullable()) typeAsString += "!";
        return typeAsString;
    }
}
