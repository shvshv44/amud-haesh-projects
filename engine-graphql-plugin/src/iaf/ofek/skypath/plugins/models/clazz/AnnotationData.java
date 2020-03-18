package iaf.ofek.skypath.plugins.models.clazz;

import javafx.util.Pair;

import java.util.ArrayList;

public class AnnotationData {

    private String name;

    private ArrayList<String> imports;
    private ArrayList<Pair<String,String>> parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }

    public ArrayList<Pair<String, String>> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Pair<String, String>> parameters) {
        this.parameters = parameters;
    }
}
