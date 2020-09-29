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

    public void nameDateHeader(){
        System.out.printf("%-30s %15s %n", "Username", "Date and Time");
        System.out.println("-------------------------------------------------------------------------");
    }

    public String askChoice(){
        return("Enter R to see most recent editors, Enter H to see how many revisions each editor made, Enter E to exit");
    }
}
