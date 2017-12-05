import java.util.Scanner;

/**
 * This class is the starting point/log in screen for providers.
 *
 * @author Aislinn
 * @version 1.0
 */

public class ProviderTerminal {

    Scanner scan = new Scanner (System.in);

    /**
     * This is the log in screen for providers.
     */

    public void start() {

        //this is where control is transferred to when it is chosen.
        System.out.println("\n\n");

        System.out.println("                      _     _           ");
        System.out.println("                     (_)   | |          ");
        System.out.println(" _ __  _ __ _____   ___  __| | ___ _ __ ");
        System.out.println("| '_ \\| '__/ _ \\ \\ / / |/ _` |/ _ \\ '__|");
        System.out.println("| |_) | | | (_) \\ V /| | (_| |  __/ |   ");
        System.out.println("| .__/|_|  \\___/ \\_/ |_|\\__,_|\\___|_|   ");
        System.out.println("| |                                     ");
        System.out.println("|_|                                     ");
        System.out.println("");

        System.out.println("Please enter your provider number :  type \"quit\" to quit");

        String firstInput = scan.nextLine();

        if(firstInput.toLowerCase().equals("quit")) {
            //quiting the provider interface
            System.out.println("\n\n\n\n");
        }
        else {

            try {
                int userID = Integer.parseInt(firstInput);

                Provider attemptedProvider = DatabaseController.getProvider(userID);

                if(attemptedProvider.getUserID() != -1) {

                    System.out.println("Welcome, " + attemptedProvider.getName());

                    ProviderInterface terminal = new ProviderInterface();
                    terminal.MainMenu();
                }

            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Provider ID");
            }

            start();
        }
    }
}