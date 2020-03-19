package org.shaq.plugins.utils;

public class StringJavaFileBuilder {

    private StringBuilder builder;
    private int tabLevel;

    public StringJavaFileBuilder() {
        this.builder = new StringBuilder("");
        tabLevel = 0;
    }

    private StringJavaFileBuilder increaseTabLevel() {
        tabLevel++;
        return this;
    }

    private StringJavaFileBuilder decreaseTabLevel() {
        tabLevel--;
        if(tabLevel < 0) tabLevel = 0;
        return this;
    }

    public StringJavaFileBuilder append(String s) {
        builder.append(s);
        return this;
    }

    public StringJavaFileBuilder append(Object o) {
        builder.append(o.toString());
        return this;
    }

    public StringJavaFileBuilder space() {
        builder.append(" ");
        return this;
    }

    public StringJavaFileBuilder equals() {
        builder.append(" = ");
        return this;
    }


    public StringJavaFileBuilder openBracket() {
        builder.append("{");
        return this;
    }

    public StringJavaFileBuilder closeBracket() {
        builder.append("}");
        return this;
    }

    public StringJavaFileBuilder openParamBracket() {
        builder.append("(");
        return this;
    }

    public StringJavaFileBuilder closeParamBracket() {
        builder.append(")");
        return this;
    }

    public StringJavaFileBuilder newLine() {
        builder.append("\n");
        for(int i = 0; i < tabLevel; i++) {
            builder.append("\t");
        }
        return this;
    }

    public StringJavaFileBuilder newLineIncTab() {
       increaseTabLevel();
       newLine();
       return this;
    }

    public StringJavaFileBuilder newLineDecTab() {
        decreaseTabLevel();
        newLine();
        return this;
    }

    public StringJavaFileBuilder clear() {
        builder = new StringBuilder("");
        return this;
    }

    public String finish() {
        return builder.toString();
    }

}
