package org.shaq.plugins.generation;

import graphql.schema.idl.TypeDefinitionRegistry;
import org.shaq.plugins.models.javafile.FileJavaComponent;

import java.util.ArrayList;
import java.util.List;

public class GenerationProcessManager {

    public List<FileJavaComponent> startGeneration(TypeDefinitionRegistry graphqlSchema) {
        ArrayList <FileJavaComponent> javaComponents = new ArrayList<>();

        //TODO: implement
        System.out.println("shaq");

        return javaComponents;
    }

}
