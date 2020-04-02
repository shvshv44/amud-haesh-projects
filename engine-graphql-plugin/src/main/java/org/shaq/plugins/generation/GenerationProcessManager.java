package org.shaq.plugins.generation;

import org.shaq.plugins.models.graphql.GraphQLOperation;
import org.shaq.plugins.models.javafile.FileJavaComponent;
import org.shaq.plugins.models.javafile.clazz.ClassFile;
import org.shaq.plugins.models.javafile.clazz.FieldData;
import org.shaq.plugins.models.javafile.common.ModifierType;
import org.shaq.plugins.models.user.UserChoiceGraphQLContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GenerationProcessManager {

    private HashSet<String> usedClassNames;

    public List<FileJavaComponent> startGeneration(UserChoiceGraphQLContext userChoices) {
        ArrayList <FileJavaComponent> javaComponents = new ArrayList<>();
        usedClassNames = new HashSet<>();

        FileJavaComponent operationMainClass = createOperationClass(userChoices);
        javaComponents.add(operationMainClass);

        createAllTypesClass(userChoices, javaComponents);

        return javaComponents;
    }

    private void createAllTypesClass(UserChoiceGraphQLContext userChoices, ArrayList<FileJavaComponent> javaComponents) {

        //TODO: implement

    }

    private FileJavaComponent createOperationClass(UserChoiceGraphQLContext userChoices) {
        GraphQLOperation mainOperation = userChoices.getChoosenOperation();
        ClassFile classFile = new ClassFile();
        classFile.setName(mainOperation.getName() + mainOperation.getType().getDefaultType());

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
