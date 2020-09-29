import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InsertionSort {
    public Map sorter(ArrayList<Author> authorsList) {
        Map<String, Integer> nameCounter = new HashMap<>();
        String checkee;
        String checker;


        for (int i = 0; i < authorsList.size()-1; i++) {
            checkee = authorsList.get(i).getUsername();
            int occurrences = 0;
            if (nameCounter.containsKey(checkee)) {
                i++;
            } else {
                for (int j = 0; j < authorsList.size()-1; j++) {
                    checker = authorsList.get(j).getUsername();
                    if (checkee.equals(checker)) {
                        occurrences++;
                    } else {
                        j++;
                    }
                }
            }
            nameCounter.put(checkee, occurrences);
        }

        return nameCounter;
    }
}