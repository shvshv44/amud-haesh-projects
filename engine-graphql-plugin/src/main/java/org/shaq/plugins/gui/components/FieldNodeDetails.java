package org.shaq.plugins.gui.components;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.shaq.plugins.models.graphql.GraphQLField;
import org.shaq.plugins.models.graphql.GraphQLFieldType;

@Getter
@Setter
@NoArgsConstructor
public class FieldNodeDetails {

    private GraphQLField field;

    @Override
    public String toString() {
        return field.getName() + " : " + graphQLTypeToString();
    }

    private String graphQLTypeToString() {
        GraphQLFieldType type = field.getType();
        String typeAsString = "";
        if (type.getIsCollection()) typeAsString += "[";
        typeAsString += type.getCoreType().getName();
        if (type.getIsCollection()) typeAsString += "]";
        if (type.getIsNullable()) typeAsString += "!";
        return typeAsString;
    }

}
