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

        boolean keepRunning = true;

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
        System.out.println("   4: ReActivate Member");
        System.out.println("   5: View all members");
        System.out.println("   6: View all providers");
        System.out.println("   7: Update member information");
        System.out.println("   -1: Quit");

        switch (scan.nextLine()) {
            case "1" : createMember(); break;
            case "2" : createProvider(); break;
            case "3" : suspendMember(); break;
            case "4" : reactivateMember(); break;
            case "5" : viewAllMembers(); break;
            case "6" : viewAllProviders(); break;
            case "7" : updateMemberInformation(); break;
            case "-1" : keepRunning = false; break;
            default: ;
        }
        if (keepRunning) mainMenu();

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

    private void createMember() {

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
                createMember();
            }
            else {
                mainMenu();
            }
        }
    }

    private void createProvider() {

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
                createProvider();
            }
            else {
                mainMenu();
            }
        }

    }

    private void suspendMember() {

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

    private void reactivateMember() {
        System.out.println("Please enter ID of the User to be reactivated. -1 to quit.");

        int ID = scan.nextInt();
        scan.nextLine(); // this is needed to soak up the newline character that the previous line doesn't pick up

        if(ID != -1) {

            if(DatabaseController.getMember(ID).getUserID() != -1) {
                System.out.println("ReActivate Member " + ID + " : " + DatabaseController.getMember(ID).getName() + "? Y/N");

                if(scan.nextLine().toLowerCase().equals("y")) {
                    DatabaseController.reactivateMember(ID);
                }
                else {
                    System.out.println("Member ReActivation aborted.");
                }
            }
        }

    }

    private void viewAllMembers() {
        for (Member x : DatabaseController.members) {
            System.out.println(x.toString());
        }
    }

    private void viewAllProviders() {
        for (Provider x : DatabaseController.providers) {
            System.out.println(x.toString());
        }
    }

    private void updateMemberInformation() {

        System.out.println("Please enter the id of the member to edit. \"quit\" to exit.");

        String temp = scan.nextLine().toLowerCase();

        if(temp.equals("quit")) {
            System.out.println("Exiting Member editing...");
        }
        else {

            try{
                Member tempMember = DatabaseController.getMember(Integer.parseInt(temp));
                if(tempMember.getUserID() != -1) {
                    System.out.println(tempMember.toString());

                    System.out.println("Menu: ");
                    System.out.println("   1: Change Member First Name");
                    System.out.println("   2: Change Member Last Name");
                    System.out.println("   3: Change Member address");
                    System.out.println("   4: Change Member City");
                    System.out.println("   5: Change Member State");
                    System.out.println("   6: Change Member zipcode");
                    System.out.println("   7: Change Member email");
                    System.out.println("   8: Change Member phone number");
                    System.out.println("   \"quit\" to quit ");

                    switch(scan.nextLine()) {

                        case "1" : updateMemberFirstName(tempMember.getUserID()); break;
                        case "2" : updateMemberLastName(tempMember.getUserID()); break;
                        case "3" : updateMemberAddress(tempMember.getUserID()); break;
                        case "4" : updateMemberCity(tempMember.getUserID()); break;
                        case "5" : updateMemberState(tempMember.getUserID()); break;
                        case "6" : updateMemberZip(tempMember.getUserID()); break;
                        case "7" : updateMemberEmail(tempMember.getUserID()); break;
                        case "8" : updateMemberPhone(tempMember.getUserID()); break;

                        default:

                    }

                }
                else {
                    System.out.println("That is not a valid member ID;");
                    updateMemberInformation();
                }
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a valid ID");
                updateMemberInformation();
            }
        }
    }

    private void updateMemberFirstName(int userID) {
        System.out.println("What would you like the new first name to be?");
        DatabaseController.updateMemberFirstName(userID, scan.nextLine());
    }

    private void updateMemberLastName(int userID) {
        System.out.println("What would you like the new last name to be?");
        DatabaseController.updateMemberLastName(userID, scan.nextLine());
    }

    private void updateMemberAddress(int userID) {
        System.out.println("What would you like the new address to be?");
        DatabaseController.updateMemberAddress(userID, scan.nextLine());
    }

    private void updateMemberCity(int userID) {
        System.out.println("What would you like the new city to be?");
        DatabaseController.updateMemberCity(userID, scan.nextLine());
    }

    private void updateMemberState(int userID) {
        System.out.println("What would you like the new state to be?");
        DatabaseController.updateMemberState(userID, scan.nextLine());
    }

    private void updateMemberZip(int userID) {
        System.out.println("What would you like the new zip code to be?");
        DatabaseController.updateMemberZip(userID, scan.nextLine());
    }

    private void updateMemberPhone(int userID) {

        System.out.println("What would you like the new Phone Number to be?");
        DatabaseController.updateMemberPhone(userID, scan.nextLine());

    }

    private void updateMemberEmail(int userID) {
        System.out.println("What would you like the new Email to be?");
        DatabaseController.updateMemberEmail(userID, scan.nextLine());

    }
}
