package iaf.ofek.skypath.plugins.models.javafile.clazz;

import iaf.ofek.skypath.plugins.models.javafile.common.ModifierType;
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
