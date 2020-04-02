package generator.graphql.manipulators;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GraphqlSchemaTextManipulator {

    public List<String> splitTypeToWords(String schema) {
        List<String> schemaTypes = new ArrayList<>();
        for (String type : schema.split("[\\s:{}.] ?",-1)) {
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

    private List<String> removeEmptyStrings(Iterable<String> splited) {
        List<String> noEmptyStrings = new ArrayList<>();
        for (String curr : splited) {
            if(!curr.equals(""))
                noEmptyStrings.add(curr);
        }
        return noEmptyStrings;
    }

    public List<String> getCurrTypeWords(String query, String currObj) {
        if(query == null)
            return new ArrayList<>();
        String notPrettyQuery = query.replaceAll("... on ","on-");
        notPrettyQuery = notPrettyQuery.replaceAll("\\s"," ");
        notPrettyQuery = notPrettyQuery.replaceAll("\\(.*?\\)","");
        return splitQueryToSubWords(notPrettyQuery,currObj);
    }

    protected List<String> splitQueryToSubWords(String query, String currObj) {
        String spaceQuery = query.replaceAll("\\s"," ");
        spaceQuery = spaceQuery.replaceAll("\\{"," { ");
        spaceQuery = spaceQuery.replaceAll("}"," } ");
        spaceQuery = spaceQuery.replaceAll("  "," ");
        List<String> queryWords = removeEmptyStrings(Arrays.asList(query.split("[\\s:{}.] ?",-1)));
        List<String> words = new ArrayList<>();
        getWordsUnderCurrObj(currObj, spaceQuery, queryWords, words);
        return removeEmptyStrings(words);
    }

    private void getWordsUnderCurrObj(String currObj, String spaceQuery, List<String> queryWords, List<String> words) {
        boolean isInCurrObj = false;
        int bracketsCounter = 0;
        for (String currWord : queryWords) {
            if(isInCurrObj){
                if(bracketsCounter == 0) {
                    words.add(currWord);
                }
                if(spaceQuery.contains(" " + currWord + " {")){
                    bracketsCounter++;
                } else if(spaceQuery.contains(" " + currWord + " }")) {
                    if(bracketsCounter == 0)
                        break;
                    bracketsCounter--;
                }
            }else if(currWord.equals(currObj)||currWord.equals("on-"+currObj) || currObj == null) {
                isInCurrObj = true;
            }
        }
    }
}
