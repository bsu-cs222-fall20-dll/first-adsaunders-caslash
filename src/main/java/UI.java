import java.util.Scanner;
public class UI {
    Scanner input = new Scanner(System.in);

    public String homeBanner() {
        return ("---------------------------" +
                "|  Orwellian News Service |" +
                "|  Wikipedia Edit Finder  |" +
                "---------------------------");
    }

    public String wikipediaPageSearchInput() {
        return ("Please input the wikipedia " +
                "page you would like to visit:");
    }

    public void interaction(){

    }
}
