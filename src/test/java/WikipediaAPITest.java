import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class WikipediaAPITest {

    @Test
    public void wikipediaFirstRevisionAuthorTest() {
        WikipediaAPIParser wikipediaAPIParser = new WikipediaAPIParser();
        String revisionAuthor = wikipediaAPIParser.getFirstRevisionAuthor();
        boolean result = (revisionAuthor.equals("String"));
        Assertions.assertTrue(result);
    }
}
