package org.shaq.plugins.models.javafile.clazz;

import org.shaq.plugins.generation.javafile.builder.ClassFileBuilder;
import org.shaq.plugins.generation.javafile.builder.JavaComponentBuilder;
import org.shaq.plugins.models.javafile.FileJavaComponent;
import lombok.Data;

import java.util.ArrayList;

@Data
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

    @Override
    public JavaComponentBuilder getBuilder() {
        return new ClassFileBuilder();
    }
}
