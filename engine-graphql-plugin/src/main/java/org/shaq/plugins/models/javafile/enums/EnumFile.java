package org.shaq.plugins.models.javafile.enums;


import org.shaq.plugins.generation.javafile.builder.EnumFileBuilder;
import org.shaq.plugins.generation.javafile.builder.JavaComponentBuilder;
import org.shaq.plugins.models.javafile.FileJavaComponent;
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
