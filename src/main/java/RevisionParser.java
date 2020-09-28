import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.text.EditorKit;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class RevisionParser {
    public void revisionParserArray(String title) throws ParseException {
       SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
       ArrayList<String> userList = new ArrayList<>();
       ArrayList<Date> dateList = new ArrayList<>();

       WikipediaConnector connection = new WikipediaConnector();

       JsonParser parser = new JsonParser();
       JsonElement rootElement = parser.parse(connection.getUsersandDates(title));
       JsonObject rootObject = rootElement.getAsJsonObject();
       JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
       JsonArray metaWikiData = null;

       for (Map.Entry<String,JsonElement> entry : pages.entrySet()){
           JsonObject entryObject = entry.getValue().getAsJsonObject();
           metaWikiData = entryObject.getAsJsonArray("revisions");
       }

       for(int i=0; i < metaWikiData.size(); i++){
           userList.add(metaWikiData.get(i).getAsJsonObject().get("user").getAsString());
           dateList.add(timestampFormat.parse(metaWikiData.get(i).getAsJsonObject().get("timestamp").getAsString()));
       }

       for(int i=0; i < userList.size(); i++){
           System.out.println("Username: " + userList.get(i) + " Date and Time: " + dateList.get(i));
       }
    }

    /*public ArrayList userParserArray() throws IOException {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        ArrayList<String> userList = new ArrayList<>();

        WikipediaConnector connection = new WikipediaConnector();

        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(connection.getUsersandDates());
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray metaWikiData = null;

        for (Map.Entry<String,JsonElement> entry : pages.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            metaWikiData = entryObject.getAsJsonArray("revisions");
        }

        for(int i=0; i < metaWikiData.size(); i++){
            userList.add(metaWikiData.get(i).getAsJsonObject().get("user").getAsString());
        }

        return userList;
    }*/
}