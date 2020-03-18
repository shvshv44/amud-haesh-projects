package iaf.ofek.skypath.plugins.models.enums;

import iaf.ofek.skypath.plugins.models.clazz.AnnotationData;

public class EnumValue {

    private String name;
    private AnnotationData annotationData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnnotationData getAnnotationData() {
        return annotationData;
    }

    public void setAnnotationData(AnnotationData annotationData) {
        this.annotationData = annotationData;
    }
}
