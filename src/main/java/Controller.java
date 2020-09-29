import java.util.Scanner;

public class Controller {
    public void userInput() throws Exception {
        UI ui = new UI();
        RevisionParser revis = new RevisionParser();
        WikipediaConnector connector = new WikipediaConnector();
        InsertionSort insert = new InsertionSort();
        Scanner read = new Scanner(System.in);

        System.out.println(ui.homeBanner());
        System.out.println(ui.wikipediaPageSearchInput());
        String title = read.nextLine();
        System.out.println(ui.askChoice());
        String choice = read.nextLine();

        if(choice.equals("R")){
            revis.outputRedirect(connector.getUsersandDates(connector.convertToUrl(title)));
            ui.nameDateHeader();
            revis.revisionParserArray(connector.getUsersandDates(connector.convertToUrl(title)));
        }else if(choice.equals("H")){
            revis.outputRedirect(connector.getUsersandDates(connector.convertToUrl(title)));
            ui.nameCountHeader();
            insert.outNameCounter(insert.sortEditorsByNumberOfEdits(insert.sorter(revis.userParserArray(connector.getUsersandDates(connector.convertToUrl(title))))));
        }else if(choice.equals("E")){
            System.out.println("Goodbye!");
        }
    }
}
