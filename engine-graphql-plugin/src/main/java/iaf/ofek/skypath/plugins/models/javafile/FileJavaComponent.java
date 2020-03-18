package iaf.ofek.skypath.plugins.models.javafile;


import iaf.ofek.skypath.plugins.generation.javafile.builder.JavaComponentBuilder;

public abstract class FileJavaComponent {

    private String name;
    private String packagePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public abstract JavaComponentBuilder getBuilder();

}
