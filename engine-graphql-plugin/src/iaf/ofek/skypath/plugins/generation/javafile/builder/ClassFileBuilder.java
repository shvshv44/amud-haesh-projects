package iaf.ofek.skypath.plugins.generation.javafile.builder;

import iaf.ofek.skypath.plugins.models.clazz.AnnotationData;
import iaf.ofek.skypath.plugins.models.clazz.ClassFile;
import iaf.ofek.skypath.plugins.models.clazz.FieldData;
import iaf.ofek.skypath.plugins.models.clazz.MethodData;
import iaf.ofek.skypath.plugins.models.common.Keywords;
import iaf.ofek.skypath.plugins.models.common.ModifierType;
import iaf.ofek.skypath.plugins.models.common.Signs;
import iaf.ofek.skypath.plugins.utils.ImportCollector;
import iaf.ofek.skypath.plugins.utils.StringJavaFileBuilder;
import javafx.util.Pair;


import java.util.ArrayList;

public class ClassFileBuilder extends JavaComponentBuilder<ClassFile> {

    @Override
    protected String createFileData(StringJavaFileBuilder sb, ClassFile component, ImportCollector importCollector) {
        sb.clear();
        sb.append(ModifierType.PUBLIC).space();
        dealWithAbstract(sb, component);
        sb.append(Keywords.CLASS).space().append(component.getName()).space();
        dealWithInheritance(sb, component);
        sb.openBracket();
        sb.newLineIncTab();
        assignValues(sb,component,importCollector);
        sb.newLineDecTab();
        sb.closeBracket();
        return sb.finish();
    }

    public void dealWithInheritance(StringJavaFileBuilder sb, ClassFile component) {
        if(component.getExtendedClass() != null) {
            sb.append(Keywords.EXTENDS).space().append(component.getExtendedClass()).space();
        }
    }

    public void dealWithAbstract(StringJavaFileBuilder sb, ClassFile component) {
        if(component.isAbstract()) {
            sb.append(Keywords.ABSTRACT).space();
        }
    }

    private void assignValues(StringJavaFileBuilder sb, ClassFile component, ImportCollector importCollector) {
        sb.newLine();
        assignDataMembers(sb, component, importCollector);
        sb.newLine();
        assignMethods(sb, component, importCollector);
    }

    private void assignDataMembers(StringJavaFileBuilder sb, ClassFile component, ImportCollector importCollector) {
        for (FieldData fieldData: component.getFields()) {
            importCollector.collectImports(fieldData.getImports());
            setFieldAnnotations(sb, fieldData, importCollector);
            sb.append(fieldData.getModifierType()).space().append(fieldData.getClassName()).space().append(fieldData.getName()).append(Signs.END_OF_JAVA_LINE);
            sb.newLine();
        }
    }

    private void assignMethods(StringJavaFileBuilder sb, ClassFile component, ImportCollector importCollector) {
        for (MethodData methodData : component.getMethods()) {
            importCollector.collectImports(methodData.getImports());
            sb.newLine();
            sb.append(methodData.getModifierType()).space().append(methodData.getReturnedType()).space().append(methodData.getName()).space().openParamBracket();
            assignParameters(sb,methodData.getParameters());
            sb.closeParamBracket().space().openBracket().newLineIncTab();
            sb.append(methodData.getInnerCode());
            sb.newLineDecTab().closeBracket().newLine();
        }
    }

    private void setFieldAnnotations(StringJavaFileBuilder sb, FieldData fieldData, ImportCollector importCollector) {
        for (AnnotationData annotationData : fieldData.getAnnotations()) {
            importCollector.collectImports(annotationData.getImports());
            sb.append(Signs.ANNOTATION_OPEN).append(annotationData.getName()).space().openParamBracket();
            assignParameters(sb, annotationData.getParameters());
            sb.closeParamBracket().newLine();
        }
    }

    private void assignParameters(StringJavaFileBuilder sb, ArrayList<Pair<String, String>> parameters) {
        for (int index = 0; index < parameters.size(); index++) {
            Pair<String,String> parameter = parameters.get(index);
            sb.append(parameter.getKey()).space().append(parameter.getValue());
            if (index < parameters.size() - 1) {
                sb.append(Signs.NEXT_JAVA_VALUE).space();
            }
        }
    }

}
