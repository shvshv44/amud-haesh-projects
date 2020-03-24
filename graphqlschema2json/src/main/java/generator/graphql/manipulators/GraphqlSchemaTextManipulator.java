package generator.graphql.manipulators;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphqlSchemaTextManipulator {

    public List<String> splitTypeToWords(String schema) {
        List<String> schemaTypes = new ArrayList<>();
        for (String type : schema.split("[\\s:{}] ?",-1)) {
            schemaTypes.add(type);
        }
        return removeEmptyStrings(schemaTypes);
    }

    public List<String> splitByBrackets(String schema) {
        List<String> schemaTypes = new ArrayList<>();
        for (String type : schema.split("}")) {
            schemaTypes.add(type);
        }
        return removeEmptyStrings(schemaTypes);
    }

    private static List<String> removeEmptyStrings(Iterable<String> splited) {
        List<String> noEmptyStrings = new ArrayList<>();
        for (String curr : splited) {
            if(!curr.equals(""))
                noEmptyStrings.add(curr);
        }
        return noEmptyStrings;
    }
}
