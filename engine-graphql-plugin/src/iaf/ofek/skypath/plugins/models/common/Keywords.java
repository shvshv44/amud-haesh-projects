package iaf.ofek.skypath.plugins.models.common;

public enum Keywords {

    CLASS("class"),
    IMPORT("import"),
    ENUM("enum"),
    PACKAGE("package"),
    EXTENDS("extends"),
    ABSTRACT("abstract");



    private String keyword;

    Keywords(String keyword) {
        this.keyword = keyword;
    }

    public String keyword() {
        return keyword;
    }

    @Override
    public String toString() {
        return keyword;
    }
}
