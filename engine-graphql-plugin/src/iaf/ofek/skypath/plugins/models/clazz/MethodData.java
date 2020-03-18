package iaf.ofek.skypath.plugins.models.clazz;

import iaf.ofek.skypath.plugins.models.common.ModifierType;
import javafx.util.Pair;

import java.util.ArrayList;

public class MethodData {

    private String name;
    private String returnedType;
    private ModifierType modifierType;
    private ArrayList<Pair<String,String>> parameters;
    private String innerCode;

    private ArrayList<String> imports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnedType() {
        return returnedType;
    }

    public void setReturnedType(String returnedType) {
        this.returnedType = returnedType;
    }

    public ModifierType getModifierType() {
        return modifierType;
    }

    public void setModifierType(ModifierType modifierType) {
        this.modifierType = modifierType;
    }

    public ArrayList<Pair<String, String>> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Pair<String, String>> parameters) {
        this.parameters = parameters;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }
}
