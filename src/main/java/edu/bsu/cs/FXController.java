package edu.bsu.cs;

import javafx.scene.control.TextField;
import java.util.Map;

public class  FXController {
    public TextField title;
    UI ui = new UI();
    AuthorSorter sort = new AuthorSorter();
    WikipediaConnector connector = new WikipediaConnector();
    RevisionParser revis = new RevisionParser();

    @SuppressWarnings("unused")//Suppresses warnings about actionEvent not being used even though it is necessary
    public void mostRecent(javafx.event.ActionEvent actionEvent) throws Exception {
        revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title.getText())));
        ui.nameDateHeader();
        for(Author author : revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title.getText())))){
            System.out.printf("%-30s %30s %n", author.getUsername(), author.getTimestamp());
        }
    }

    @SuppressWarnings("unused")//Suppresses warnings about actionEvent not being used even though it is necessary
    public void mostEdits(javafx.event.ActionEvent actionEvent) throws Exception{
        revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title.getText())));
        ui.nameCountHeader();
        Map nameCounter = sort.outNameCounter(sort.sortEditorsByNumberOfEdits(sort.sorter(revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title.getText()))))));
        for(Object username : nameCounter.keySet()){
            System.out.printf("%-30s %10s %n", username.toString(), nameCounter.get(username));
        }
    }
}
