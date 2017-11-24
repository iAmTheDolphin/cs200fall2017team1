import java.awt.*;
import java.util.Scanner;

//this will be like the starting thing i guess?

/**
 steps
 display login screen, with a box to enter provider number and a button to click if operator
 if operator, switch to operatorterminal
 if provider, validate-- read in provider number text file and turn into array
 then it switches to the next thing for providers, who have these options:
 give service
 open service file
 generate provider record
 open provider records
 log out
*/

public class ProviderTerminal {

    Scanner scan = new Scanner (System.in);

    public void start() {

        //this is where control is transferred to when it is chosen.
        System.out.println("\n\n\n\n");

        System.out.println(" _    _      _                          _ ");
        System.out.println("| |  | |    | |                        | |");
        System.out.println("| |  | | ___| | ___ ___  _ __ ___   ___| |");
        System.out.println("| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |");
        System.out.println("\\  /\\  /  __/ | (_| (_) | | | | | |  __/_|");
        System.out.println(" \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___(_)");
        System.out.println("");

        System.out.println("Please enter your provider number : ");

        int providerNum = -1;

        providerNum = scan.nextInt();

        while(providerNum < 0) {
            System.out.println("Invalid Provider ID. Please enter in a valid ID.");
            providerNum = scan.nextInt();
        }

        System.out.println("validating provider number...");

        Provider provider = DatabaseController.getProvider(providerNum);

        if(provider.getUserID() != -1) {

            System.out.println("Welcome, " + provider.getName());
            ProviderInterface temp = new ProviderInterface();
            temp.MainMenu();

        }
        else {
            System.out.println("\nSorry, that isn't a valid ID.");
            start();
        }


    }


}