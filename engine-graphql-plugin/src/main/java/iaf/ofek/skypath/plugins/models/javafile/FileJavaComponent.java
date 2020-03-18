package iaf.ofek.skypath.plugins.models.javafile;


import iaf.ofek.skypath.plugins.generation.javafile.builder.JavaComponentBuilder;
import lombok.Data;

@Data
public abstract class FileJavaComponent {

    private String name;
    private String packagePath;

    public abstract JavaComponentBuilder getBuilder();

}
