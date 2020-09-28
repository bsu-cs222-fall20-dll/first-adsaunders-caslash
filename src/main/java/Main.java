import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        RevisionParser revis = new RevisionParser();
        InsertionSort insert = new InsertionSort();
        Controller control = new Controller();

        //revis.revisionParserArray();
        //changes.recentChanges();

        /*for(int i=0; i<insert.sorter(revis.userParserArray()).size(); i++){
            System.out.println("Username: " +insert.sorter(revis.userParserArray()).get(i));
        }*/

        control.userInput();


    }
}
