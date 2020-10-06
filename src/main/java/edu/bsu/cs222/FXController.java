package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Map;

@SuppressWarnings("all")//Suppresses warnings about actionEvent not being used even though it is necessary
public class  FXController {
    public TextField title;
    public HBox doubleVBoxParent;
    public VBox usernameVBox;
    public VBox infoVBox;
    public VBox redirectionMessVBox;
    public Button numOfEditsButton;
    public Button recencyButton;
    AuthorSorter sort = new AuthorSorter();
    WikipediaConnector connector = new WikipediaConnector();
    RevisionParser revis = new RevisionParser();

    public void mostRecent(javafx.event.ActionEvent actionEvent) throws Exception{
        Label usernameHead = new Label("Username");
        Label editHead = new Label("Edits Made");
        usernameVBox.getChildren().add(usernameHead);
        infoVBox.getChildren().add(editHead);
        getMostRecentList();
    }

    public void mostEdits(javafx.event.ActionEvent actionEvent) throws Exception{
        Label usernameHead = new Label("Username");
        Label editHead = new Label("Edits Made");
        usernameVBox.getChildren().add(usernameHead);
        infoVBox.getChildren().add(editHead);
        getMostEditsList();
    }


    public void getMostEditsList() throws Exception {
        Map<String, Integer> nameCounter;
        labelPlacements();
        nameCounter = sort.outNameCounter(sort.sortEditorsByNumberOfEdits(sort.sorter(revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title.getText()))))));
        for(Object username : nameCounter.keySet()){
            Label usernameLabel = new Label(username.toString());
            usernameVBox.getChildren().add(usernameLabel);
            Label editsMade = new Label(nameCounter.get(username).toString());
            infoVBox.getChildren().add(editsMade);
        }
        recencyButton.setDisable(true);
        numOfEditsButton.setDisable(true);
    }

    public void getMostRecentList() throws Exception {
        revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title.getText())));
        labelPlacements();
        for(Author author : revis.revisionParserArray(connector.connectToWikipedia(connector.convertToUrl(title.getText())))) {
            Label usernameLabel = new Label(author.getUsername());
            usernameVBox.getChildren().add(usernameLabel);
            Label editsMade = new Label(author.getTimestamp());
            infoVBox.getChildren().add(editsMade);
        }
        recencyButton.setDisable(true);
        numOfEditsButton.setDisable(true);

    }

    public void labelPlacements() throws Exception {
        if(revis.constructArrayOfRevisions(connector.connectToWikipedia(connector.convertToUrl(title.getText()))) == null){
            Label doesntExist = new Label("This page does not exist please try again");
            redirectionMessVBox.getChildren().add(doesntExist);
        }else{
            Label redirected = new Label(revis.outputRedirect(connector.connectToWikipedia(connector.convertToUrl(title.getText()))));
            redirectionMessVBox.getChildren().add(redirected);
        }
    }

    public void refreshLists(ActionEvent actionEvent) {
        usernameVBox.getChildren().clear();
        infoVBox.getChildren().clear();
        redirectionMessVBox.getChildren().clear();
        recencyButton.setDisable(false);
        numOfEditsButton.setDisable(false);
    }
}
