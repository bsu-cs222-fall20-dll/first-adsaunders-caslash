import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Controller {
    public void userInput() throws IOException {
        WikipediaConnector connector = new WikipediaConnector();
        Scanner read = new Scanner(System.in);
        System.out.println("Enter name of Wikipedia page.");
        String title = read.nextLine();
        Reader wikiPage = connector.getUsersandDates(title);

        if(wikiPage == null){
            System.out.println("Could not connect to Wikipedia, please try again.");
        }

    }
}
