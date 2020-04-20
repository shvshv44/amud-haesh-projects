package org.shaq.plugins.gui.components;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseGraphQLOperationComponent {

    private String name;
    private String operationType;
    private String returnType;

    @Override
    public String toString() {
        return operationType + ":        " + name + " : " + returnType;
    }
}
