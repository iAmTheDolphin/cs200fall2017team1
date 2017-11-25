/*
 * Parker Jones
 * 11/24/17
 * Operator Interface
 */

import java.util.Scanner;

public class OperatorInterface {

    Scanner scan = new Scanner(System.in);

    public OperatorInterface() {



    }

    public void mainMenu() {

        System.out.println("\n\n");

        System.out.println("                            _              ");
        System.out.println("                           | |            ");
        System.out.println("  ___  _ __   ___ _ __ __ _| |_ ___  _ __ ");
        System.out.println(" / _ \\| '_ \\ / _ \\ '__/ _` | __/ _ \\| '__|");
        System.out.println("| (_) | |_) |  __/ | | (_| | || (_) | |   ");
        System.out.println(" \\___/| .__/ \\___|_|  \\__,_|\\__\\___/|_|   ");
        System.out.println("      | |                                 ");
        System.out.println("      |_|                                 ");
        System.out.println("");

        System.out.println("Menu: ");
        System.out.println("   1: Create New Member");
        System.out.println("   2: Create New Provider");
        System.out.println("   3: Suspend Member");

        switch (scan.nextLine()) {
            case "1" : CreateMember(); break;
            case "2" : CreateProvider(); break;
            case "3" : SuspendMember(); break;
            default: ;
        }
        mainMenu();

    }

    private void pause() {
        try
        {
            Thread.sleep(300);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private void CreateMember() {

        pause();
        System.out.println("Please enter the first name of the Member.");
        String firstName = scan.nextLine();
        pause();
        System.out.println("Please enter the last name of the Member.");
        String lastName = scan.nextLine();
        pause();
        System.out.println("Please enter the address of the Member.");
        String address = scan.nextLine();
        pause();
        System.out.println("Please enter the city the member lives in.");
        String city = scan.nextLine();
        pause();
        System.out.println("Please enter the state the Member lives in.");
        String state = scan.nextLine();
        pause();
        System.out.println("Please enter the Member's zip code.");
        String zip = scan.nextLine();
        pause();
        System.out.println("Please enter the Member's email.");
        String email = scan.nextLine();
        pause();
        System.out.println("Please enter the Member's phone number.");
        String phone = scan.nextLine();
        pause();

        System.out.println(firstName + " " + lastName);
        System.out.println(address);
        System.out.println(city);
        System.out.println(state);
        System.out.println(zip);
        System.out.println(email);
        System.out.println(phone);

        System.out.println("Is this correct? Y/N");
        String inputContinue = scan.nextLine();

        if(inputContinue.toLowerCase().equals("y")) {
            int temp = DatabaseController.newMember(firstName, lastName, address, city, state, zip, email, phone).getUserID();
            System.out.println("New Member created with ID of " + temp);

        }
        else {
            System.out.println("Do you want to retry creating a new member? Y/N");
            String inputCont2 = scan.nextLine();
            if(inputCont2.toLowerCase().equals("y")) {
                CreateMember();
            }
            else {
                mainMenu();
            }
        }
    }

    private void CreateProvider() {

        pause();
        System.out.println("Please enter the first name of the Provider.");
        String firstName = scan.nextLine();
        pause();
        System.out.println("Please enter the last name of the Provider.");
        String lastName = scan.nextLine();
        pause();
        System.out.println("Please enter the address of the Provider.");
        String address = scan.nextLine();
        pause();
        System.out.println("Please enter the city the Provider lives in.");
        String city = scan.nextLine();
        pause();
        System.out.println("Please enter the state the Provider lives in.");
        String state = scan.nextLine();
        pause();
        System.out.println("Please enter the Provider's zip code.");
        String zip = scan.nextLine();
        pause();
        System.out.println("Please enter the Provider's email.");
        String email = scan.nextLine();
        pause();
        System.out.println("Please enter the Provider's phone number.");
        String phone = scan.nextLine();
        pause();

        System.out.println(firstName + " " + lastName);
        System.out.println(address);
        System.out.println(city);
        System.out.println(state);
        System.out.println(zip);
        System.out.println(email);
        System.out.println(phone);

        System.out.println("Is this correct? Y/N");
        String inputContinue1 = scan.nextLine();

        if(inputContinue1.toLowerCase().equals("y")) {
            int temp = DatabaseController.newProvider(firstName, lastName, address, city, state, zip, email, phone).getUserID();
            System.out.println("New Provider created with ID of " + temp);

        }
        else {
            System.out.println("Do you want to retry creating a new Provider? Y/N");
            String inputCont3 = scan.nextLine();
            if(inputCont3.toLowerCase().equals("y")) {
                CreateProvider();
            }
            else {
                mainMenu();
            }
        }

    }

    private void SuspendMember() {

        System.out.println("Please enter ID of the User to be suspended. -1 to quit.");

        int ID = scan.nextInt();
        scan.nextLine(); // this is needed to soak up the newline character that the previous line doesn't pick up

        if(ID != -1) {

            if(DatabaseController.getMember(ID).getUserID() != -1) {
                System.out.println("Suspend Member " + ID + " : " + DatabaseController.getMember(ID).getName() + "? Y/N");

                if(scan.nextLine().toLowerCase().equals("y")) {
                    DatabaseController.suspendMember(ID);
                }
                else {
                    System.out.println("Member suspension aborted.");
                }
            }
        }
    }
}
