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
            revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title)));
            ui.nameDateHeader();
            for(Author author : revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title)))){
                System.out.printf("%-30s %30s %n", author.getUsername(), author.getTimestamp());
            }
        }else if(choice.equals("H")){
            revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title)));
            ui.nameCountHeader();
            insert.outNameCounter(insert.sortEditorsByNumberOfEdits(insert.sorter(revis.userParserArray(connector.connectToWikipedia(connector.convertToUrl(title))))));
        }else if(choice.equals("E")){
            System.out.println("Goodbye!");
        }
    }
}
