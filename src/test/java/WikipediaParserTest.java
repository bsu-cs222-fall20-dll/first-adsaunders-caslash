import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;

public class WikipediaParserTest {
    @Test
    public void testJsonReader() throws IOException {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        boolean result = rootElement.isJsonObject();
        Assertions.assertTrue(result);
    }
/*
    @Test
    public void testJsonFormatterNamesAndDates(){
        JsonFormatter jsonFormatter = new JsonFormatter();
        String output = jsonFormatter.formatNamesAndDates;
        boolean result = (output.equals(expectedResult));
        Assertions.assertTrue(result);
    }

    @Test
    public void testJsonFormatterNamesAndRevisionsCount(){
        JsonFormatter jsonFormatter = new JsonFormatter();
        String output = jsonFormatter.formatNamesAndRevisions;
        boolean result = (output.equals(expectedResult));
        Assertions.assertTrue(result);
    }*/
}
