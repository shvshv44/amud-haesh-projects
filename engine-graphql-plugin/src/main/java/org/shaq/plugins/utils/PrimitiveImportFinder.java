package org.shaq.plugins.utils;

import java.util.HashMap;

public class PrimitiveImportFinder {

    private HashMap<String,String> imports;

    public PrimitiveImportFinder() {
        this.imports = new HashMap<>();
        put(Integer.class);
        put(Double.class);
        put(Boolean.class);
        put(Float.class);
        put(String.class);
    }

    public String getImport(String className) {
        return imports.get(className);
    }

    public void put(Class c) {
        imports.put(c.getSimpleName(), c.getPackage().getName() + "." + c.getSimpleName());
    }

}
