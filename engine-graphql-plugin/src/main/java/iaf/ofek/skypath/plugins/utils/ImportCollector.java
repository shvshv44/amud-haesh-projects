package iaf.ofek.skypath.plugins.utils;

import iaf.ofek.skypath.plugins.models.javafile.common.Keywords;
import iaf.ofek.skypath.plugins.models.javafile.common.Signs;

import java.util.ArrayList;
import java.util.List;

public class ImportCollector {

    private ArrayList<String> imports;

    public ImportCollector() {
        this.imports = new ArrayList<>();
    }

    public void addImport(String path){
        if(!imports.contains(path) && path != null){
            imports.add(path);
        }
    }

    public void collectImports(List<String> imports) {
        for(String importPath : imports) {
          addImport(importPath);
        }
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }

    public void assignImports(StringJavaFileBuilder sb) {
        for (String importPath : imports) {
            sb.append(Keywords.IMPORT).space().append(importPath).append(Signs.END_OF_JAVA_LINE).newLine();
        }
    }
}
