import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
//API changes: /w/api.php?action=feedrecentchanges&limit=20&titles=Soup&format=json
//API editors: /w/api.php?action=query&prop=contributors&pclimit=20&titles=Soup&format=json

public class RevisionParser {
    public static void main(String[] args) throws IOException {
        URL oracle = new URL("https://en.wikipedia.org/w/api.php?action=query&prop=contributors&pclimit=20&titles=Soup&format=json");
        URLConnection connection = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}