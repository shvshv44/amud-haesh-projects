package org.shaq.plugins.gui.windows;

import com.intellij.ui.components.JBList;
import lombok.Data;
import org.shaq.plugins.gui.components.ChooseGraphQLOperationComponent;
import org.shaq.plugins.models.graphql.GraphQLFieldType;
import org.shaq.plugins.models.graphql.GraphQLGenerationContext;
import org.shaq.plugins.models.graphql.GraphQLOperation;
import org.shaq.plugins.models.graphql.enums.GraphQLOperationType;

import javax.swing.*;
import java.awt.*;
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

    private JList<ChooseGraphQLOperationComponent> operationJList;

    public void fillSchema(GraphQLGenerationContext context) {

        chooseOperationPanel.setLayout(new GridLayout(1,1));
        operationJList = initializeComponentList(context);

        fillChoosingListWithOperations(context.getQueries());
        fillChoosingListWithOperations(context.getMutations());
        chooseOperationPanel.add(operationJList);
    }

    private void fillChoosingListWithOperations(Map<String, GraphQLOperation> operations) {
        DefaultListModel  model = (DefaultListModel) (operationJList.getModel());
        for (GraphQLOperation operation : operations.values()) {
            ChooseGraphQLOperationComponent component = new ChooseGraphQLOperationComponent();
            component.setName(operation.getName());
            component.setOperationType(operation.getType().getNameInSchema());
            component.setReturnType(graphQLTypeToString(operation.getReturnType()));
            model.addElement(component);
        }
    }


    private JList<ChooseGraphQLOperationComponent> initializeComponentList(GraphQLGenerationContext context) {
        JList<ChooseGraphQLOperationComponent> operationJList = new JBList<>(new DefaultListModel<>());
        operationJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        operationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        operationJList.addListSelectionListener((listSelectionEvent) -> updateChoosenOperationData(context, listSelectionEvent.getFirstIndex()));
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

    private void updateChoosenOperationData(GraphQLGenerationContext context, int operationIndex) {
        ChooseGraphQLOperationComponent operationComponent = operationJList.getModel().getElementAt(operationIndex);
        GraphQLOperation operation = getGraphQLTypeFromChoosenComponent(context, operationComponent);
        updateChooseParametersPanel(context, operation);
        updateChooseFieldsPanel(context, operation);
    }

    private void updateChooseFieldsPanel(GraphQLGenerationContext context, GraphQLOperation operation) {
        //TODO: implement
    }

    private void updateChooseParametersPanel(GraphQLGenerationContext context, GraphQLOperation operation) {
        //TODO: implement
    }

    private GraphQLOperation getGraphQLTypeFromChoosenComponent(GraphQLGenerationContext context, ChooseGraphQLOperationComponent operationComponent) {
        if (operationComponent.getOperationType().equals(GraphQLOperationType.QUERY.getNameInSchema())) {
            return context.getQueries().get(operationComponent.getName());
        }

        return context.getMutations().get(operationComponent.getName());
    }

}
