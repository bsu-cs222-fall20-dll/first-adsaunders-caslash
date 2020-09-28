import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InsertionSort {
    public Map sorter(ArrayList<String> A) {
        Map<String, Integer> nameCounter = new HashMap<>();
        String checkee;
        String checker;


        for (int i = 0; i < A.size(); i++) {
            int occurrences = 0;
            checkee = A.get(i);
            if (nameCounter.containsKey(checkee)) {
                i++;
            } else {
                for (int j = 0; j < A.size(); j++) {
                    checker = A.get(j);
                    if (checkee.equals(checker)) {
                        occurrences++;
                    } else {
                        j++;
                    }
                }
                nameCounter.put(checkee, occurrences);
            }
        }

        return nameCounter;
    }
}