package org.shaq.plugins.generation;

import org.shaq.plugins.models.graphql.GraphQLField;
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
    private boolean lombokSupport;
    private boolean serializedNameSupport;

    public List<FileJavaComponent> startGeneration(UserChoiceGraphQLContext userChoices, ProjectModel projectModel) {
        javaComponents = new ArrayList<>();
        usedClassNames = new HashSet<>();
        lombokSupport = userChoices.isLombokSupport();
        serializedNameSupport = userChoices.isSerializedNameSupport();

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
        ClassFile classFile = createEmptyClassFile(convertToClassName(classChoosenName), projectModel.getPackageName());

        for (GraphQLChoosenField choosenField : rootField.getInnerChoosenFields()) {
            FieldData fieldData = createEmptyFieldDataFromChoosenType (choosenField);
            String choosenName = chooseClassNameByFather(rootField, choosenField);
            fieldData.setClassName(choosenName);
            usedClassNames.add(choosenName);
            classFile.getFields().add(fieldData);
            createAllTypesClass(choosenField,choosenName,projectModel);
        }

        javaComponents.add(classFile);
    }

    private String chooseClassNameByFather(GraphQLChoosenField fatherField, GraphQLChoosenField fieldToChooseName) {
        boolean isPremitive = fieldToChooseName.getOriginalField().getType().getCoreType().getIsScalar();
        String classSimpleName = convertToClassName(fieldToChooseName.getOriginalField().getType().getCoreType().getName());
        if (!isPremitive && usedClassNames.contains(classSimpleName))
            return convertToClassName(fatherField.getName()) + classSimpleName;

        return classSimpleName;
    }

    private FieldData createEmptyFieldDataFromChoosenType(GraphQLChoosenField choosenField) {
        FieldData fieldData = new FieldData();
        fieldData.setName(choosenField.getName());
        fieldData.setModifierType(ModifierType.PRIVATE);
        fieldData.setList(choosenField.getOriginalField().getType().getIsCollection());
        fieldData.setAnnotations(new ArrayList<>());
        fieldData.setImports(new ArrayList<>());
        return fieldData;
    }

    private ClassFile createEmptyClassFile(String className, String packageName) {
        ClassFile classFile = new ClassFile();
        classFile.setPackagePath(packageName);
        classFile.setName(className);
        classFile.setImports(new ArrayList<>());
        classFile.setMethods(new ArrayList<>());
        classFile.setFields(new ArrayList<>());
        return classFile;
    }

    private void buildEnumField(GraphQLChoosenField rootField, String classChoosenName, ProjectModel projectModel) {
        EnumFile enumFile = new EnumFile();
        enumFile.setName(convertToClassName(classChoosenName));
        enumFile.setPackagePath(projectModel.getPackageName());
        enumFile.setValues(new ArrayList<>());

        GraphQLSimpleType enumType = rootField.getOriginalField().getType().getCoreType();
        for (GraphQLField gqlEnumValue : enumType.getFields().values()) {
            EnumValue enumValue = new EnumValue();
            enumValue.setName(gqlEnumValue.getName());
            enumFile.getValues().add(enumValue);
            //TODO: serializedName when implement
        }

        javaComponents.add(enumFile);
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
        classFile.setName(convertToClassName(mainOperation.getName() + mainOperation.getType().getDefaultType()));
        classFile.setPackagePath(projectModel.getPackageName());

        FieldData rootField = new FieldData();
        rootField.setName(userChoices.getRootField().getName());
        rootField.setModifierType(ModifierType.PRIVATE);
        rootField.setList(userChoices.getRootField().getOriginalField().getType().getIsCollection());
        rootField.setAnnotations(new ArrayList<>());

        String rootClassName = convertToClassName(userChoices.getRootField().getOriginalField().getType().getCoreType().getName());
        rootField.setClassName(rootClassName);
        usedClassNames.add(rootClassName);

        ArrayList<FieldData> fields = new ArrayList<>();
        fields.add(rootField);
        classFile.setFields(fields);

        return classFile;
    }

    private String convertToClassName(String name) {
        if( name == null || name.length() <= 0)
            return name;
        if (name.length() == 1)
            return name.toUpperCase();

        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

}
