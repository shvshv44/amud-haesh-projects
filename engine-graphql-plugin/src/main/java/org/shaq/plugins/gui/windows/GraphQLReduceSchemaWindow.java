package org.shaq.plugins.gui.windows;

import com.intellij.ui.components.JBList;
import lombok.Data;
import org.shaq.plugins.gui.components.ChooseGraphQLOperationComponent;
import org.shaq.plugins.models.graphql.GraphQLFieldType;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;
import org.shaq.plugins.models.graphql.GraphQLOperation;

import javax.swing.*;
import java.awt.*;

@Data
public class GraphQLReduceSchemaWindow {

    private JLabel helpText;
    private JPanel reducePanel;
    private JPanel mainPanel;
    private JPanel chooseFieldsPanel;
    private JScrollPane chooseFieldsPanelScroll;
    private JPanel chooseParametersPanel;
    private JScrollPane chooseParametersScroll;
    private JScrollPane chooseOperationScroll;
    private JPanel chooseOperationPanel;


    public void fillSchema(GraphQLGenerationContext context) {
        // TODO: implement

        chooseOperationPanel.setLayout(new GridLayout(1,1));
        JList<ChooseGraphQLOperationComponent> operationJList = new JBList<>(new DefaultListModel<>());
        operationJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        operationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultListModel  model = (DefaultListModel) (operationJList.getModel());
        for (GraphQLOperation query : context.getQueries().values()) {
            ChooseGraphQLOperationComponent component = new ChooseGraphQLOperationComponent();
            component.setName(query.getName());
            component.setOperationType(query.getType().getNameInSchema());
            component.setReturnType(graphQLTypeToString(query.getReturnType()));
            model.addElement(component);
        }
        for (GraphQLOperation mutation : context.getMutations().values()) {
            ChooseGraphQLOperationComponent component = new ChooseGraphQLOperationComponent();
            component.setName(mutation.getName());
            component.setOperationType(mutation.getType().getNameInSchema());
            component.setReturnType(graphQLTypeToString(mutation.getReturnType()));
            model.addElement(component);
        }
        chooseOperationPanel.add(operationJList);
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
