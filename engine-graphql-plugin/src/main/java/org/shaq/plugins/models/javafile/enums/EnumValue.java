package org.shaq.plugins.models.javafile.enums;

import org.shaq.plugins.models.javafile.clazz.AnnotationData;
import lombok.Data;

@Data
public class EnumValue {

    private String name;
    private AnnotationData annotationData;

}
