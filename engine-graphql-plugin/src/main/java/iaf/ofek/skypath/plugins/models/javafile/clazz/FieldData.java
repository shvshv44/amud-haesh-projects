package iaf.ofek.skypath.plugins.models.javafile.clazz;

import iaf.ofek.skypath.plugins.models.javafile.common.ModifierType;
import java.util.ArrayList;
import java.util.List;

public class FieldData {

    private String name;
    private ModifierType modifierType;
    private String className;
    private boolean isList;
    private List<AnnotationData> annotations;

    private ArrayList<String> imports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModifierType getModifierType() {
        return modifierType;
    }

    public void setModifierType(ModifierType modifierType) {
        this.modifierType = modifierType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    public List<AnnotationData> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationData> annotations) {
        this.annotations = annotations;
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }
}
