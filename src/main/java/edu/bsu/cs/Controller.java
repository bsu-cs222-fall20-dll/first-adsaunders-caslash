package edu.bsu.cs;

import java.util.Map;
import java.util.Scanner;

public class Controller {
    public void userInput() throws Exception {
        UI ui = new UI();
        AuthorSorter sort = new AuthorSorter();
        Scanner read = new Scanner(System.in);
        WikipediaConnector connector = new WikipediaConnector();
        System.out.println(ui.homeBanner());
        System.out.println(ui.wikipediaPageSearchInput());
        String title = read.nextLine();
        System.out.println(ui.askChoice());
        String choice = read.nextLine();

        RevisionParser revis = new RevisionParser();

        if(choice.equals("R")){
            revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title)));
            ui.nameDateHeader();
            for(Author author : revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title)))){
                System.out.printf("%-30s %30s %n", author.getUsername(), author.getTimestamp());
            }
        }else if(choice.equals("H")){
            revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title)));
            ui.nameCountHeader();
            Map nameCounter = sort.outNameCounter(sort.sortEditorsByNumberOfEdits(sort.sorter(revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title))))));
            for(Object username : nameCounter.keySet()){
                System.out.printf("%-30s %10s %n", username.toString(), nameCounter.get(username));
            }
        }else if(choice.equals("E")){
            System.out.println("Goodbye!");
        }
    }
}
