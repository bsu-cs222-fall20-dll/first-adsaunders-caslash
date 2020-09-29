import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.platform.commons.util.StringUtils;

import javax.swing.text.EditorKit;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class RevisionParser {
    @SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
    public void revisionParserArray(InputStream inputStream) throws ParseException {
       ArrayList<String> userList = new ArrayList<>();
       ArrayList<Date> dateList = new ArrayList<>();
       Reader reader = new InputStreamReader(inputStream);

       JsonParser parser = new JsonParser();
       JsonElement rootElement = parser.parse(reader);
       JsonObject rootObject = rootElement.getAsJsonObject();
       JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
       JsonArray metaWikiData = null;

       for (Map.Entry<String,JsonElement> entry : pages.entrySet()){
           JsonObject entryObject = entry.getValue().getAsJsonObject();
           metaWikiData = entryObject.getAsJsonArray("revisions");
       }

       ArrayList<Author> listOfAuthors = new ArrayList<>();

       for(JsonElement author : metaWikiData){
           String user = author.getAsJsonObject().get("user").getAsString();
           String timestamp = author.getAsJsonObject().get("timestamp").getAsString();
           Author newAuthor = new Author(user, timestamp);
           listOfAuthors.add(newAuthor);
       }

       for(int i=0; i < listOfAuthors.size(); i++){
           System.out.printf("%-30s %30s %n", listOfAuthors.get(i).getUsername(), listOfAuthors.get(i).getTimestamp());
       }
    }

    @SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
    public ArrayList userParserArray(InputStream inputStream) throws IOException {
        ArrayList<Author> listOfAuthors = new ArrayList<>();
        Reader reader = new InputStreamReader(inputStream);

        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray metaWikiData = null;

        for (Map.Entry<String,JsonElement> entry : pages.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            metaWikiData = entryObject.getAsJsonArray("revisions");
        }

        for(JsonElement author : metaWikiData){
            String user = author.getAsJsonObject().get("user").getAsString();
            String timestamp = author.getAsJsonObject().get("timestamp").getAsString();
            Author newAuthor = new Author(user, timestamp);
            listOfAuthors.add(newAuthor);
        }

        return listOfAuthors;
    }
}