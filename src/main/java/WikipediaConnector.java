import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class WikipediaConnector {
    public Reader getUsersandDates(String title){
        try {
            URL oracle = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + title + "&rvprop=timestamp|user&rvlimit=20&redirects");
            java.net.URLConnection connection = oracle.openConnection();
            Reader in = new InputStreamReader(connection.getInputStream());
            return in;
        }catch (Exception e){
            System.out.println("Could not connect to Wikipedia, please try again.");
            return null;
        }
    }
}
