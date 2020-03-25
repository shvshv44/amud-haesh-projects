package org.shaq.plugins.gui.windows;

import com.intellij.ui.components.JBList;
import lombok.Data;
import org.shaq.plugins.gui.components.ChooseGraphQLOperationComponent;
import org.shaq.plugins.models.graphql.GraphQLFieldType;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;
import org.shaq.plugins.models.graphql.GraphQLOperation;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

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

        chooseOperationPanel.setLayout(new GridLayout(1,1));
        JList<ChooseGraphQLOperationComponent> operationJList = initalizeComponentList();

        fillChoosingListWithOperations(operationJList, context.getQueries());
        fillChoosingListWithOperations(operationJList, context.getMutations());
        chooseOperationPanel.add(operationJList);
    }

    private void fillChoosingListWithOperations(JList<ChooseGraphQLOperationComponent> operationJList, Map<String, GraphQLOperation> operations) {
        DefaultListModel  model = (DefaultListModel) (operationJList.getModel());
        for (GraphQLOperation operation : operations.values()) {
            ChooseGraphQLOperationComponent component = new ChooseGraphQLOperationComponent();
            component.setName(operation.getName());
            component.setOperationType(operation.getType().getNameInSchema());
            component.setReturnType(graphQLTypeToString(operation.getReturnType()));
            model.addElement(component);
        }
    }


    private JList<ChooseGraphQLOperationComponent> initalizeComponentList() {
        JList<ChooseGraphQLOperationComponent> operationJList = new JBList<>(new DefaultListModel<>());
        operationJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        operationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return operationJList;
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
