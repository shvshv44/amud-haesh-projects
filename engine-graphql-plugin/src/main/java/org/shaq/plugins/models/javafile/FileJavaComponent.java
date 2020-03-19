package org.shaq.plugins.models.javafile;


import org.shaq.plugins.generation.javafile.builder.JavaComponentBuilder;
import lombok.Data;

@Data
public abstract class FileJavaComponent {

    private String name;
    private String packagePath;

    public abstract JavaComponentBuilder getBuilder();

}
