import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public class WikipediaParserTest {

    @Test
    public void testJsonFormatterNamesAndDates() throws ParseException {
        RevisionParser revis = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        ArrayList<Author> listOfAuthors = revis.revisionParserArray(inputStream);
        String output = listOfAuthors.get(0).getUsername();
        String outputTwo = listOfAuthors.get(0).getTimestamp();
        boolean result = (output.equals("Chenopodiaceous"));
        boolean resultTwo = (outputTwo.equals("Mon Sep 28 23:30:08 EDT 2020"));
        Assertions.assertTrue(result && resultTwo);
    }


    @Test
    public void testJsonFormatterRevisionsCount(){
        RevisionParser revis = new RevisionParser();
        AuthorSorter sort = new AuthorSorter();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        ArrayList<Author> listOfAuthors = revis.revisionParserArray(inputStream);
        Map nameCounter = sort.outNameCounter(sort.sortEditorsByNumberOfEdits(sort.sorter(listOfAuthors)));
        String output = nameCounter.get("Chenopodiaceous").toString();
        boolean result = (output.equals("2"));
        Assertions.assertTrue(result);
    }
}
