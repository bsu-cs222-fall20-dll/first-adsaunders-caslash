package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("deprecation") //Gets rid of warnings for soon-to-be obsolete classes in the API
public class RevisionParser {
    public ArrayList<Author> revisionParserArray(InputStream inputStream){
        ArrayList<Author> listOfAuthors = new ArrayList<>();
        JsonArray metaWikiData = constructArrayOfRevisions(inputStream);

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

    public JsonArray constructArrayOfRevisions(InputStream inputStream){
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray metaWikiData = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            if (entryObject.getAsJsonObject().has("missing")) {
                return null;
            } else {
                metaWikiData = entryObject.getAsJsonArray("revisions");
            }
        }
        return metaWikiData;
    }

    public String outputRedirect(InputStream inputStream){
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject query = rootObject.getAsJsonObject("query");

        try{
            return getRedirectMessage(query);
        }catch (NullPointerException e){
            return("You have not been redirected");
        }
    }

    public String getRedirectMessage(JsonObject query){
        JsonArray fromToData;
        fromToData = query.getAsJsonArray("redirects");
        for(JsonElement fromTo : fromToData){
            String from = fromTo.getAsJsonObject().get("from").getAsString();
            String to = fromTo.getAsJsonObject().get("to").getAsString();
            if(from.length()>0 && to.length()>0){
                return("You have been redirected from " + from + " to " + to);
            }
        }
        return null;
    }
}