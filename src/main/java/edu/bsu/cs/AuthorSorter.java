package edu.bsu.cs;

import java.util.*;

public class AuthorSorter {
    public Map<String, Integer> sorter(ArrayList<Author> authorsList) {
        Map<String, Integer> nameCounter = new HashMap<>();

        for(int i=0; i<authorsList.size()-1; i++){
            int occurrences = 0;
            String checkee = authorsList.get(i).getUsername();
            if (!nameCounter.containsKey(checkee)){
                for (int j=0; j<authorsList.size()-1; j++){
                    String checker = authorsList.get(j).getUsername();
                    if (checker.equals(checkee)){
                        occurrences++;
                    }
                }
                nameCounter.put(checkee,occurrences);
            }
        }
        return nameCounter;
    }

    //Used from dtpitts<dtpitts@bsu.edu> and jddeffendal2<jddeffendal2@bsu.edu>
    public Map<String, Integer> sortEditorsByNumberOfEdits(Map<String, Integer> editCountMap) {
        List<Map.Entry<String, Integer>> countedEditList = new LinkedList<>(editCountMap.entrySet());
        countedEditList.sort((editOne, editTwo) -> (editTwo.getValue()).compareTo(editOne.getValue()));
        Map<String, Integer> nameCounter = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> edits : countedEditList) {
            nameCounter.put(edits.getKey(), edits.getValue());
        }
        return nameCounter;
    }

    public Map<String, Integer> outNameCounter(Map<String, Integer> nameCounter){
        return nameCounter;
    }
}
