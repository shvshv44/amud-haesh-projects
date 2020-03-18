package iaf.ofek.skypath.plugins.models.javafile.enums;


import iaf.ofek.skypath.plugins.generation.javafile.builder.EnumFileBuilder;
import iaf.ofek.skypath.plugins.generation.javafile.builder.JavaComponentBuilder;
import iaf.ofek.skypath.plugins.models.javafile.FileJavaComponent;
import lombok.Data;
;

import java.util.ArrayList;

@Data
public class EnumFile extends FileJavaComponent {

    private ArrayList<EnumValue> values;

    @Override
    public JavaComponentBuilder getBuilder() {
        return new EnumFileBuilder();
    }
}
