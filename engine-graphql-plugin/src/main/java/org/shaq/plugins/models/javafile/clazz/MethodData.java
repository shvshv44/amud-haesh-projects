package org.shaq.plugins.models.javafile.clazz;

import org.shaq.plugins.models.javafile.common.ModifierType;
import javafx.util.Pair;
import lombok.Data;

import java.util.ArrayList;

@Data
public class MethodData {

    private String name;
    private String returnedType;
    private ModifierType modifierType;
    private ArrayList<Pair<String,String>> parameters;
    private String innerCode;

    private ArrayList<String> imports;

}
