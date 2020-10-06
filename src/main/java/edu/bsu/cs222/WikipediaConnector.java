package edu.bsu.cs222;

import java.io.InputStream;
import java.net.URL;

public class WikipediaConnector {
    public InputStream connectToWikipedia(URL url){
        try {
            java.net.URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (caslash@bsu.edu)");
            return connection.getInputStream();
        }catch (Exception e){
            return null;
        }
    }

    public URL convertToUrl(String title) throws Exception {
        String convertedTitle = title.replaceAll(" ", "%20");
        return new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + convertedTitle + "&rvprop=timestamp|user&rvlimit=20&redirects");
    }
}