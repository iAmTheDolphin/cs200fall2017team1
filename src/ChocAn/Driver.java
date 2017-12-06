package ChocAn;/*
 * Parker Jones
 * 11/21/17
 * Driver Class
 */

import java.util.Scanner;

/**
 * this method is the main menu of the whole Chocan System
 * @author Parker Jones
 * @version 1.0
 */
public class Driver {

    /**
     * this is the Main method and the Main Menu of the whole ChocAn system
     * @param args
     */
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
            System.out.println("       -1: Quit                                 ");

            try{
                userSelection = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e ) {
                System.out.println("ERROR: NUMBER INPUT EXCEPTION. " + e);
                userSelection = -1;
            }

            if(userSelection == 1) {

                // start the Provider Terminal
                System.out.println("starting the provider Terminal...");
                ProviderTerminal terminal = new ProviderTerminal();
                terminal.start();
                userSelection = -1;

            }
            else if( userSelection == 2) {
                // start the Operator Terminal
                System.out.println("starting the operator Terminal...");
                OperatorInterface terminal = new OperatorInterface();
                terminal.mainMenu();
                userSelection = -1;
            }
            else if (userSelection == 3) {
                // start the GUI
                System.out.println("starting the GUI...");
            }
            else if(userSelection == -1) {
                System.out.println("Thank you for using ChocAn Systems!");
                break;
            }
            else {
                userSelection = -1;
                System.out.println("ERROR: That was not a valid selection. Please Try again.");
            }
        }
    }
}
