package iaf.ofek.skypath.plugins.models.enums;


import iaf.ofek.skypath.plugins.generation.javafile.builder.EnumFileBuilder;
import iaf.ofek.skypath.plugins.generation.javafile.builder.JavaComponentBuilder;
import iaf.ofek.skypath.plugins.models.FileJavaComponent;
;

import java.util.ArrayList;

public class EnumFile extends FileJavaComponent {

    private ArrayList<EnumValue> values;

    public ArrayList<EnumValue> getValues() {
        return values;
    }

    public void setValues(ArrayList<EnumValue> values) {
        this.values = values;
    }

    @Override
    public JavaComponentBuilder getBuilder() {
        return new EnumFileBuilder();
    }
}
