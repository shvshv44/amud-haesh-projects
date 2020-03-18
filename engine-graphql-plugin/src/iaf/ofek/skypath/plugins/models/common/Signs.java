package iaf.ofek.skypath.plugins.models.common;

public enum Signs {

    END_OF_JAVA_LINE(";"),
    ANNOTATION_OPEN("@"),
    NEXT_JAVA_VALUE(",");

    private String sign;

    Signs(String sign) {
        this.sign = sign;
    }

    public String sign() {
        return sign;
    }

    @Override
    public String toString() {
        return sign;
    }

}
