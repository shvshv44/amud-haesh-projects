package iaf.ofek.skypath.plugins.models.clazz;

import iaf.ofek.skypath.plugins.generation.javafile.builder.ClassFileBuilder;
import iaf.ofek.skypath.plugins.generation.javafile.builder.JavaComponentBuilder;
import iaf.ofek.skypath.plugins.models.FileJavaComponent;
import java.util.ArrayList;

public class ClassFile extends FileJavaComponent {

    private String extendedClass;
    private boolean isAbstract;

    private ArrayList<FieldData> fields;
    private ArrayList<MethodData> methods;
    private ArrayList<String> imports;

    public ClassFile() {
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.imports = new ArrayList<>();
    }

    public String getExtendedClass() {
        return extendedClass;
    }

    public void setExtendedClass(String extendedClass) {
        this.extendedClass = extendedClass;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public ArrayList<FieldData> getFields() {
        return fields;
    }

    public void setFields(ArrayList<FieldData> fields) {
        this.fields = fields;
    }

    public ArrayList<MethodData> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<MethodData> methods) {
        this.methods = methods;
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }

    @Override
    public JavaComponentBuilder getBuilder() {
        return new ClassFileBuilder();
    }
}
