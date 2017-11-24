/*
 * Parker Jones
 * 11/21/17
 * Driver Class
 */

import java.util.Scanner;

public class Driver {

    public static void main (String[] args) {

        DatabaseController.setup();

        Scanner scan = new Scanner (System.in);

        int userSelection = -1;

        while (userSelection == -1) {

            System.out.println("--------------------------------------------------------------------------");
            System.out.println("                 _____ _                 ___            ");
            System.out.println("                /  __ \\ |               / _ \\         ");
            System.out.println("                | /  \\/ |__   ___   ___/ /_\\ \\_ __   ");
            System.out.println("                | |   | '_ \\ / _ \\ / __|  _  | '_ \\  ");
            System.out.println("                | \\__/\\ | | | (_) | (__| | | | | | |  ");
            System.out.println("                 \\____/_| |_|\\___/ \\___\\_| |_/_| |_|");
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------");

            System.out.println(" Please enter the number of your selection. ");
            System.out.println("    Menu :                                     ");
            System.out.println("       1: Provider Terminal                    ");
            System.out.println("       2: Operator Terminal                    ");
            System.out.println("       3: GUI Mode                             ");

            userSelection = scan.nextInt();

            if(userSelection == 1) {

                // start the Provider Terminal
                System.out.println("starting the provider Terminal...");
                ProviderTerminal terminal = new ProviderTerminal();
                terminal.start();

            }
            else if( userSelection == 2) {
                // start the Operator Terminal
                System.out.println("starting the operator Terminal...");
            }
            else if (userSelection == 3) {
                // start the GUI
                System.out.println("starting the GUI...");
            }
            else {
                userSelection = -1;
                System.out.println("ERROR: That was not a valid selection. Please Try again.");
            }
        }
    }
}
