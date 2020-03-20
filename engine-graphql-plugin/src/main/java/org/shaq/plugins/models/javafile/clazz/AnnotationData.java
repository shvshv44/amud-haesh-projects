package org.shaq.plugins.models.javafile.clazz;

import javafx.util.Pair;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AnnotationData {

    private String name;
    private ArrayList<String> imports;
    private ArrayList<Pair<String,String>> parameters;

}
