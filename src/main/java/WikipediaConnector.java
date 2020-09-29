import java.io.InputStream;
import java.net.URL;

public class WikipediaConnector {
    public InputStream getUsersandDates(URL url){
        try {
            java.net.URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (caslash@bsu.edu)");
            return connection.getInputStream();
        }catch (Exception e){
            System.out.println("Could not connect to Wikipedia, please try again.");
        }
        return null;
    }

    public URL convertToUrl(String title) throws Exception {
       String convertedTitle = title.replaceAll(" ", "%20");
       URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + convertedTitle + "&rvprop=timestamp|user&rvlimit=20&redirects");
       return url;
    }
}
