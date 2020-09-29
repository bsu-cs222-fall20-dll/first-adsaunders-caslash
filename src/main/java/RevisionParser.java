import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
public class RevisionParser {

    public ArrayList<Author> revisionParserArray(InputStream inputStream){
        ArrayList<Author> listOfAuthors = new ArrayList<>();
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray metaWikiData = null;

        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            if (entryObject.getAsJsonObject().has("missing")) {
                System.out.println("This page does not exist please try again");
            } else {
                metaWikiData = entryObject.getAsJsonArray("revisions");
            }
        }

        if(metaWikiData != null){
            for(JsonElement author : metaWikiData){
                String user = author.getAsJsonObject().get("user").getAsString();
                String timestamp = author.getAsJsonObject().get("timestamp").getAsString();
                Author newAuthor = new Author(user, timestamp);
                listOfAuthors.add(newAuthor);
            }
        }
        return listOfAuthors;
    }

    public void outputRedirect(InputStream inputStream){
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject query = rootObject.getAsJsonObject("query");
        JsonArray fromToData = null;

        try{
            fromToData = query.getAsJsonArray("redirects");

            for(JsonElement fromTo : fromToData){
                String from = fromTo.getAsJsonObject().get("from").getAsString();
                String to = fromTo.getAsJsonObject().get("to").getAsString();
                if(from.length()>0 && to.length()>0){
                    System.out.println("You have been redirected from " + from + " to " + to);
                }
            }
        }catch (NullPointerException e){
            System.out.println("You have not been redirected");
        }
    }
}