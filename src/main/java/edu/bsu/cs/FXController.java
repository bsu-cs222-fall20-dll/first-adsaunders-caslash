package edu.bsu.cs;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Map;

public class  FXController {
    public TextField title;
    public VBox parentBox;
    UI ui = new UI();
    AuthorSorter sort = new AuthorSorter();
    WikipediaConnector connector = new WikipediaConnector();
    RevisionParser revis = new RevisionParser();

    @SuppressWarnings("all")//Suppresses warnings about actionEvent not being used even though it is necessary
    public void mostRecent(javafx.event.ActionEvent actionEvent) throws Exception {
        HBox header = new HBox(new Label(ui.NDHeader()));
        parentBox.getChildren().add(header);
        getMostRecentList();
    }

    @SuppressWarnings("all")//Suppresses warnings about actionEvent not being used even though it is necessary
    public void mostEdits(javafx.event.ActionEvent actionEvent) throws Exception{
        HBox header = new HBox(new Label(ui.NCHeader()));
        parentBox.getChildren().add(header);
        getMostEditsList();
    }

    @SuppressWarnings("all")//Suppresses warnings about username variable
    public void getMostEditsList() throws Exception {
        Map<String, Integer> nameCounter = null;
        revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title.getText())));
        ui.nameCountHeader();
        if(revis.constructArrayOfRevisions(connector.connectToWikipedia(connector.convertToUrl(title.getText()))) == null){
            System.out.println("This page does not exist please try again");
        }else{
            nameCounter = sort.outNameCounter(sort.sortEditorsByNumberOfEdits(sort.sorter(revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title.getText()))))));
        }
        for(Object username : nameCounter.keySet()){
            HBox revision = new HBox(new Label(String.format("%-50s %d\n", username.toString(), nameCounter.get(username))));
            parentBox.getChildren().add(revision);
        }
    }

    public void getMostRecentList() throws Exception {
        revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title.getText())));
        ui.nameDateHeader();
        for(Author author : revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title.getText())))) {
            HBox revision = new HBox(new Label(String.format("%20s" +author.getUsername(), author.getTimestamp())));
            parentBox.getChildren().add(revision);
        }
    }

}
