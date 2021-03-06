package org.shaq.plugins.gui.windows;

import com.intellij.ui.components.JBList;
import lombok.Data;
import org.shaq.plugins.exceptions.GraphQLUserChoiceException;
import org.shaq.plugins.gui.components.*;
import org.shaq.plugins.models.graphql.*;
import org.shaq.plugins.models.graphql.enums.GraphQLOperationType;
import org.shaq.plugins.models.user.GraphQLChoosenField;
import org.shaq.plugins.models.user.UserChoiceGraphQLContext;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private GraphQLGenerationContext context;
    private JList<ChooseGraphQLOperationComponent> operationJList;
    private JCheckBoxTree fieldTree;

    public void fillSchema(GraphQLGenerationContext context) {
        this.context = context;
        chooseOperationPanel.setLayout(new GridLayout(1,1));
        chooseFieldsPanel.setLayout(new GridLayout(1,1));
        chooseParametersPanel.setLayout(new GridLayout(1,1));

        operationJList = initializeComponentList();
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


    private JList<ChooseGraphQLOperationComponent> initializeComponentList() {
        JList<ChooseGraphQLOperationComponent> operationJList = new JBList<>(new DefaultListModel<>());
        operationJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        operationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        operationJList.addListSelectionListener((listSelectionEvent) -> updateChoosenOperationData(operationJList.getSelectedIndex()));
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

    private void updateChoosenOperationData(int operationIndex) {
        ChooseGraphQLOperationComponent operationComponent = operationJList.getModel().getElementAt(operationIndex);
        GraphQLOperation operation = getGraphQLTypeFromChoosenComponent(operationComponent);
        updateChooseParametersPanel(operation);
        updateChooseFieldsPanel(operation);
    }

    private void updateChooseFieldsPanel(GraphQLOperation operation) {
        GraphQLField rootField = new GraphQLField();
        rootField.setName(operation.getName());
        rootField.setType(operation.getReturnType());
        fieldTree = new JCheckBoxTree(createNodesFromFields(rootField));
        chooseFieldsPanel.removeAll();
        chooseFieldsPanel.add(fieldTree);
        chooseFieldsPanel.updateUI();
    }

    private DefaultMutableTreeNode createNodesFromFields(GraphQLField field) {
        FieldNodeDetails details = new FieldNodeDetails();
        details.setField(field);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(details);
        GraphQLSimpleType coreType = field.getType().getCoreType();
        if (!coreType.getIsScalar() && !coreType.getIsEnum() && coreType.getFields().size() > 0 ) {
            for (GraphQLField child : coreType.getFields().values()) {
                root.add(createNodesFromFields(child));
            }
        }

        return root;
    }

    private void updateChooseParametersPanel(GraphQLOperation operation) {
        chooseParametersPanel.removeAll();
        if(operation.getParameters() != null && operation.getParameters().values().size() > 0) {
            chooseParametersPanel.setLayout(new BoxLayout(chooseParametersPanel,BoxLayout.Y_AXIS));
            for (GraphQLParameter parameter : operation.getParameters().values()) {
                ParameterCheckBoxPanel checkBoxPanel = new ParameterCheckBoxPanel(parameter);
                chooseParametersPanel.add(checkBoxPanel);
            }
        }

        chooseParametersPanel.updateUI();
    }

    private GraphQLOperation getGraphQLTypeFromChoosenComponent(ChooseGraphQLOperationComponent operationComponent) {
        if (operationComponent.getOperationType().equals(GraphQLOperationType.QUERY.getNameInSchema())) {
            return context.getQueries().get(operationComponent.getName());
        }

        return context.getMutations().get(operationComponent.getName());
    }

    public UserChoiceGraphQLContext getUserChoiceContext() {
        UserChoiceGraphQLContext userChoice = new UserChoiceGraphQLContext();
        setOperationChoice(userChoice);
        setParametersChoice(userChoice);
        setFieldsChoice(userChoice);

        userChoice.setTypes(context.getTypes());
        return userChoice;
    }

    private void setFieldsChoice(UserChoiceGraphQLContext userChoice) {
        HashMap<Integer, List<TreePath>> pathByLevel = dividePathsByLevel(fieldTree.getCheckedPaths());
        TreePath rootPath = pathByLevel.get(1).get(0);
        GraphQLField field = getFieldFromPath(rootPath);
        GraphQLChoosenField choosenField = createChoosenFieldFromField(field);
        if (fieldTree.getCheckedPaths().length > 1)
            buildFieldTreeFromRoot(choosenField, pathByLevel, 2);

        userChoice.setRootField(choosenField);
    }

    private GraphQLChoosenField createChoosenFieldFromField(GraphQLField field) {
        GraphQLChoosenField choosenField = new GraphQLChoosenField();
        choosenField.setName(field.getName());
        choosenField.setOriginalField(field);
        choosenField.setInnerChoosenFields(new ArrayList<>());
        return choosenField;
    }

    private void buildFieldTreeFromRoot(GraphQLChoosenField rootField, HashMap<Integer, List<TreePath>> pathByLevel, int currentlevel) {
        List<TreePath> currentLevelPaths = pathByLevel.get(currentlevel);

        if (currentLevelPaths != null && currentLevelPaths.size() > 0) {
            for (TreePath treePath : currentLevelPaths) {
                GraphQLField currentField = getFieldFromPath(treePath);
                GraphQLField currentParentField = getFieldFromPath(treePath.getParentPath());
                if (currentParentField.getName().equals(rootField.getName())) {
                    GraphQLChoosenField choosenField = createChoosenFieldFromField(currentField);
                    rootField.getInnerChoosenFields().add(choosenField);
                    buildFieldTreeFromRoot(choosenField, pathByLevel, currentlevel + 1);
                }
            }
        }
    }

    private GraphQLField getFieldFromPath(TreePath treePath) {
        DefaultMutableTreeNode lastComponentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
        FieldNodeDetails fieldNodeDetails = (FieldNodeDetails) lastComponentNode.getUserObject();
        return fieldNodeDetails.getField();
    }


    private  HashMap<Integer, List<TreePath>> dividePathsByLevel(TreePath[] checkedPaths) {
        HashMap<Integer, List<TreePath>> pathByLevel = new HashMap<>();
        for (TreePath treePath : checkedPaths) {
            int pathLevel = treePath.getPathCount();
            if(pathByLevel.get(pathLevel) == null)
                pathByLevel.put(pathLevel, new ArrayList<>());

            pathByLevel.get(pathLevel).add(treePath);
        }

        return pathByLevel;
    }

    private void setParametersChoice(UserChoiceGraphQLContext userChoice) {
        GraphQLOperation choosenOperation = userChoice.getChoosenOperation();
        userChoice.setChoosenParameters(new HashMap<>());
        if (chooseParametersPanel != null && chooseParametersPanel.getComponents() != null && chooseParametersPanel.getComponents().length > 0) {
            for (Component component : chooseParametersPanel.getComponents()) {
                ParameterCheckBoxPanel parameterCheckBox = (ParameterCheckBoxPanel) component;
                if(parameterCheckBox.hasParameterBeenChoosen()) {
                    GraphQLParameter parameter = choosenOperation.getParameters().get(parameterCheckBox.getParameterName());
                    if (parameter == null)
                        throw new GraphQLUserChoiceException("Parameter " + parameterCheckBox.getParameterName() + " does not exist in " + choosenOperation.getName());

                    userChoice.getChoosenParameters().put(parameter.getName(), parameter);
                }
            }
        }
    }

    private void setOperationChoice(UserChoiceGraphQLContext userChoice) {
        int selectedOperationIndex = operationJList.getSelectedIndex();
        ChooseGraphQLOperationComponent operationComponent = operationJList.getModel().getElementAt(selectedOperationIndex);
        GraphQLOperation operation = null;
        if (operationComponent.getOperationType().equals(GraphQLOperationType.QUERY.getNameInSchema()))
            operation = context.getQueries().get(operationComponent.getName());
        else
            operation = context.getMutations().get(operationComponent.getName());

        if (operation == null)
            throw new GraphQLUserChoiceException("Operation of type " + operationComponent.getName() + " cannot be handled!");

        userChoice.setChoosenOperation(operation);
    }

}
