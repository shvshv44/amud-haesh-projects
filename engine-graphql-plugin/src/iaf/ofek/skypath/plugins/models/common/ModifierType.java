package iaf.ofek.skypath.plugins.models.common;

public enum ModifierType {

    PUBLIC("public"),
    PROTECTED("protected"),
    PRIVATE("private"),
    DEFAULT("");

    private String modifierName;

    ModifierType(String modifierName) {
        this.modifierName = modifierName;
    }

    public String modifierName() {
        return modifierName;
    }

    @Override
    public String toString() {
        return modifierName;
    }

}
