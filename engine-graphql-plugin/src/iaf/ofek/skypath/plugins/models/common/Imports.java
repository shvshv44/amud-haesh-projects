package iaf.ofek.skypath.plugins.models.common;

public enum Imports {

    SERIALIZED_NAME("com.google.gson.annotations.SerializedName");

    private String path;

    Imports(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }

}
