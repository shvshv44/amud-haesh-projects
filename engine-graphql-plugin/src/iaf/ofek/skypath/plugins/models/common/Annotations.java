package iaf.ofek.skypath.plugins.models.common;

public enum Annotations {

    SERIALIZED_NAME("SerializedName");

    private String annotName;

    Annotations(String annotName) {
        this.annotName = annotName;
    }

    public String annotName() {
        return annotName;
    }

    @Override
    public String toString() {
        return annotName;
    }

}
