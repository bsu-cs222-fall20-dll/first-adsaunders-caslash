

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UITest {

    @Test
    public void HomeBannerTest() {
        UI ui = new UI();
        String homeBanner = ui.homeBanner();
        boolean result = (homeBanner.equals("---------------------------" +
                "|  Orwellian News Service |" +
                "|   Wikipedia Edit Parser |" +
                "---------------------------"));
        Assertions.assertTrue(result);
    }

}
