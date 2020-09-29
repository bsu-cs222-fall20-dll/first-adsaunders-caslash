import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Author {
    public String username;
    public String timestamp;

    public Author(String username, String timestamp){
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getUsername(){
        return username;
    }

    public String getTimestamp() throws ParseException {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        return timestampFormat.parse(timestamp).toString();
    }
}
