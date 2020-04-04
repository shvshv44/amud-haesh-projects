package org.shaq.plugins.generation;

import cucumber.api.java.gl.E;
import gherkin.lexer.En;
import org.shaq.plugins.models.graphql.GraphQLFieldType;
import org.shaq.plugins.models.graphql.GraphQLOperation;
import org.shaq.plugins.models.graphql.GraphQLSimpleType;
import org.shaq.plugins.models.javafile.FileJavaComponent;
import org.shaq.plugins.models.javafile.clazz.ClassFile;
import org.shaq.plugins.models.javafile.clazz.FieldData;
import org.shaq.plugins.models.javafile.common.ModifierType;
import org.shaq.plugins.models.javafile.enums.EnumFile;
import org.shaq.plugins.models.javafile.enums.EnumValue;
import org.shaq.plugins.models.logic.ProjectModel;
import org.shaq.plugins.models.user.GraphQLChoosenField;
import org.shaq.plugins.models.user.UserChoiceGraphQLContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GenerationProcessManager {

    private HashSet<String> usedClassNames;
    private ArrayList<FileJavaComponent> javaComponents;

    public List<FileJavaComponent> startGeneration(UserChoiceGraphQLContext userChoices, ProjectModel projectModel) {
        javaComponents = new ArrayList<>();
        usedClassNames = new HashSet<>();

        FileJavaComponent operationMainClass = createOperationClass(userChoices, projectModel);
        javaComponents.add(operationMainClass);

        createAllTypesClass(userChoices.getRootField(), userChoices.getRootField().getOriginalField().getType().getCoreType().getName() , projectModel);

        return javaComponents;
    }

    private void createAllTypesClass(GraphQLChoosenField rootField, String classChoosenName, ProjectModel projectModel) {

        GraphQLSimpleType fieldType = rootField.getOriginalField().getType().getCoreType();

        if(isFieldEnum(rootField))
            buildEnumField(rootField, classChoosenName, projectModel);
        else if (isFieldRegularClass(rootField)) {
            buildRegularClassFields(rootField, classChoosenName, projectModel);
        }
    }

    private void buildRegularClassFields(GraphQLChoosenField rootField, String classChoosenName, ProjectModel projectModel) {
        //TODO : implemwent
    }

    private void buildEnumField(GraphQLChoosenField rootField, String classChoosenName, ProjectModel projectModel) {
        EnumFile enumFile = new EnumFile();
        enumFile.setName(classChoosenName);
        enumFile.setPackagePath(projectModel.getPackageName());
        enumFile.setValues(new ArrayList<>());

        for (GraphQLChoosenField enumValue : rootField.getInnerChoosenFields()) {
            //TODO : set enums
        }
    }

    private boolean isFieldEnum (GraphQLChoosenField field) {
        return field.getOriginalField().getType().getCoreType().getIsEnum() &&
                !field.getOriginalField().getType().getCoreType().getIsScalar();
    }

    private boolean isFieldRegularClass (GraphQLChoosenField field) {
        return !field.getOriginalField().getType().getCoreType().getIsEnum() &&
                !field.getOriginalField().getType().getCoreType().getIsScalar();
    }

    private FileJavaComponent createOperationClass(UserChoiceGraphQLContext userChoices, ProjectModel projectModel) {
        GraphQLOperation mainOperation = userChoices.getChoosenOperation();
        ClassFile classFile = new ClassFile();
        classFile.setName(mainOperation.getName() + mainOperation.getType().getDefaultType());
        classFile.setPackagePath(projectModel.getPackageName());

        FieldData rootField = new FieldData();
        rootField.setName(userChoices.getRootField().getName());
        rootField.setModifierType(ModifierType.PRIVATE);
        rootField.setList(userChoices.getRootField().getOriginalField().getType().getIsCollection());

        String rootClassName = userChoices.getRootField().getOriginalField().getType().getCoreType().getName();
        rootField.setClassName(rootClassName);
        usedClassNames.add(rootClassName);

        ArrayList<FieldData> fields = new ArrayList<>();
        fields.add(rootField);
        classFile.setFields(fields);

        return classFile;
    }

}
