package org.shaq.plugins.models.javafile.clazz;

import org.shaq.plugins.models.javafile.common.ModifierType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FieldData {

    private String name;
    private ModifierType modifierType;
    private String className;
    private boolean isList;
    private List<AnnotationData> annotations;
    private ArrayList<String> imports;

}
