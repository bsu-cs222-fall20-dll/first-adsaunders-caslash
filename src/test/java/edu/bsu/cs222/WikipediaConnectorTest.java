package edu.bsu.cs222;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.InputStream;
import java.util.ArrayList;

public class WikipediaConnectorTest {

    @Test
    public void testWikipediaConnects() throws Exception {
        WikipediaConnector wikipediaConnector = new WikipediaConnector();
        RevisionParser revis = new RevisionParser();
        InputStream inputStream = wikipediaConnector.connectToWikipedia(wikipediaConnector.convertToUrl("soup"));
        ArrayList<Author> output = revis.revisionParserArray(inputStream);
        boolean result = !output.isEmpty();
        Assertions.assertTrue(result);
    }

    @Test
    public void testWikipediaRedirects() throws Exception {
        WikipediaConnector wikipediaConnector = new WikipediaConnector();
        RevisionParser revis = new RevisionParser();
        InputStream inputStream = wikipediaConnector.connectToWikipedia(wikipediaConnector.convertToUrl("zappa"));
        ArrayList<Author> output = revis.revisionParserArray(inputStream);
        boolean result = !output.isEmpty();
        Assertions.assertTrue(result);
    }

    @Test
    public void testWikipediaPageDoesNotExist() throws Exception {
        WikipediaConnector wikipediaConnector = new WikipediaConnector();
        RevisionParser revis = new RevisionParser();
        InputStream inputStream = wikipediaConnector.connectToWikipedia(wikipediaConnector.convertToUrl("ausdyfg"));
        ArrayList<Author> output = revis.revisionParserArray(inputStream);
        boolean result = output.isEmpty();
        Assertions.assertTrue(result);
    }

    //This test only works if computer does not have internet connection.
    /*
    @Test
    public void testWikipediaNetworkNotEstablished() throws Exception {
        WikipediaConnector wikipediaConnector = new WikipediaConnector();
        InputStream inputStream = wikipediaConnector.connectToWikipedia(wikipediaConnector.convertToUrl(""));
        boolean result = (inputStream == null);
        Assertions.assertTrue(result);
    }*/
}
