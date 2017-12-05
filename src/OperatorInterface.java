/*
 * Parker Jones and Hunter James
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
        System.out.println("   7: Update Member information");
        System.out.println("   8: Update Provider information");
        System.out.println("   9: Delete Member");
        System.out.println("  10: Delete Provider");
        System.out.println("   -1: Quit");

        switch (scan.nextLine()) {
            case "1" : createMember(); break;
            case "2" : createProvider(); break;
            case "3" : suspendMember(); break;
            case "4" : reactivateMember(); break;
            case "5" : viewAllMembers(); break;
            case "6" : viewAllProviders(); break;
            case "7" : updateMemberInformation(); break;
            case "8" : updateProviderInformation(); break;
            case "9" : deleteMember(); break;
            case "10": deleteProvider(); break;
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
                System.out.println("Suspend Member " + ID + ": " + DatabaseController.getMember(ID).getName() + "? Y/N");

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
            System.out.println( x.toDisplayString());
        }
    }

    private void viewAllProviders() {
        for (User x : DatabaseController.providers) {
            System.out.println(x.toDisplayString());
        }
    }

    private void updateMemberInformation() {

        System.out.println("Please enter the ID of the member to edit. \"quit\" to exit.");

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
                    System.out.println("   3: Change Member Street Address");
                    System.out.println("   4: Change Member City");
                    System.out.println("   5: Change Member State");
                    System.out.println("   6: Change Member Zip Code");
                    System.out.println("   7: Change Member Email");
                    System.out.println("   8: Change Member Phone Number");
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

    private void updateMemberFirstName(int memberID) {
        System.out.println("What would you like the new first name to be?");
        DatabaseController.updateMemberFirstName(memberID, scan.nextLine());
    }

    private void updateMemberLastName(int memberID) {
        System.out.println("What would you like the new last name to be?");
        DatabaseController.updateMemberLastName(memberID, scan.nextLine());
    }

    private void updateMemberAddress(int memberID) {
        System.out.println("What would you like the new street address to be?");
        DatabaseController.updateMemberAddress(memberID, scan.nextLine());
    }

    private void updateMemberCity(int memberID) {
        System.out.println("What would you like the new city to be?");
        DatabaseController.updateMemberCity(memberID, scan.nextLine());
    }

    private void updateMemberState(int memberID) {
        System.out.println("What would you like the new state to be?");
        DatabaseController.updateMemberState(memberID, scan.nextLine());
    }

    private void updateMemberZip(int memberID) {
        System.out.println("What would you like the new zip code to be?");
        DatabaseController.updateMemberZip(memberID, scan.nextLine());
    }

    private void updateMemberPhone(int memberID) {

        System.out.println("What would you like the new phone number to be?");
        DatabaseController.updateMemberPhone(memberID, scan.nextLine());

    }

    private void updateMemberEmail(int memberID) {
        System.out.println("What would you like the new email to be?");
        DatabaseController.updateMemberEmail(memberID, scan.nextLine());

    }


    private void updateProviderInformation() {

        System.out.println("Please enter the ID of the Provider to edit. \"quit\" to exit.");

        String temp = scan.nextLine().toLowerCase();

        if(temp.equals("quit")) {
            System.out.println("Exiting Provider editing...");
        }
        else {

            try{
                Provider tempProvider = DatabaseController.getProvider(Integer.parseInt(temp));
                if(tempProvider.getUserID() != -1) {
                    System.out.println(tempProvider.toString());

                    System.out.println("Menu: ");
                    System.out.println("   1: Change Provider First Name");
                    System.out.println("   2: Change Provider Last Name");
                    System.out.println("   3: Change Provider Street Address");
                    System.out.println("   4: Change Provider City");
                    System.out.println("   5: Change Provider State");
                    System.out.println("   6: Change Provider Zip Code");
                    System.out.println("   7: Change Provider Email");
                    System.out.println("   8: Change Provider Phone Number");
                    System.out.println("   \"quit\" to quit ");

                    switch(scan.nextLine()) {

                        case "1" : updateProviderFirstName(tempProvider.getUserID()); break;
                        case "2" : updateProviderLastName(tempProvider.getUserID()); break;
                        case "3" : updateProviderAddress(tempProvider.getUserID()); break;
                        case "4" : updateProviderCity(tempProvider.getUserID()); break;
                        case "5" : updateProviderState(tempProvider.getUserID()); break;
                        case "6" : updateProviderZip(tempProvider.getUserID()); break;
                        case "7" : updateProviderEmail(tempProvider.getUserID()); break;
                        case "8" : updateProviderPhone(tempProvider.getUserID()); break;

                        default:

                    }

                }
                else {
                    System.out.println("That is not a valid provider ID;");
                    updateProviderInformation();
                }
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a valid ID");
                updateProviderInformation();
            }
        }
    }

    private void updateProviderFirstName(int userID) {
        System.out.println("What would you like the new first name to be?");
        DatabaseController.updateProviderFirstName(userID, scan.nextLine());
    }

    private void updateProviderLastName(int userID) {
        System.out.println("What would you like the new last name to be?");
        DatabaseController.updateProviderLastName(userID, scan.nextLine());
    }

    private void updateProviderAddress(int userID) {
        System.out.println("What would you like the new street address to be?");
        DatabaseController.updateProviderAddress(userID, scan.nextLine());
    }

    private void updateProviderCity(int userID) {
        System.out.println("What would you like the new city to be?");
        DatabaseController.updateProviderCity(userID, scan.nextLine());
    }

    private void updateProviderState(int userID) {
        System.out.println("What would you like the new state to be?");
        DatabaseController.updateProviderState(userID, scan.nextLine());
    }

    private void updateProviderZip(int userID) {
        System.out.println("What would you like the new zip code to be?");
        DatabaseController.updateProviderZip(userID, scan.nextLine());
    }

    private void updateProviderPhone(int userID) {

        System.out.println("What would you like the new phone number to be?");
        DatabaseController.updateProviderPhone(userID, scan.nextLine());

    }

    private void updateProviderEmail(int userID) {
        System.out.println("What would you like the new email to be?");
        DatabaseController.updateProviderEmail(userID, scan.nextLine());

    }

    private void deleteMember() {
    		System.out.println("Please enter the ID of the Member to delete. Enter \"quit\" to exit.");
        String temp = scan.nextLine().toLowerCase();
        if(temp.equals("quit")) {
            System.out.println("Exiting Member deletion...");
        }
        else {
        		try{ 
                Member tempMember = DatabaseController.getMember(Integer.parseInt(temp));
                if(tempMember.getUserID() != -1) {
                		System.out.println("Delete Member " + tempMember.getUserID() + ": " + DatabaseController.getMember(tempMember.getUserID()).getName() + "? Y/N");
                			if(scan.nextLine().toLowerCase().equals("y")) {
                				System.out.println("Deleting Member...");
//                        		System.out.println(tempMember.toString());
                        		DatabaseController.deleteMember(tempMember.getUserID());
                        }
                        else {
                            System.out.println("Member deletion aborted.");
                        }
                    }
                else {
                    System.out.println("That is not a valid member ID;");
                    deleteMember();
                }
        		}
            
            catch(NumberFormatException e) {
                System.out.println("Please enter a valid ID");
                deleteMember();
            	}		
        	}
    }
    
    private void deleteProvider() {
		System.out.println("Please enter the ID of the Provider to delete. Enter \"quit\" to exit.");
		String temp = scan.nextLine().toLowerCase();
		if(temp.equals("quit")) {
			System.out.println("Exiting Provider deletion...");
		}
		else {
    			try{ 
    				Provider tempProvider = DatabaseController.getProvider(Integer.parseInt(temp));
    				if(tempProvider.getUserID() != -1) {
    					System.out.println("Delete Provider " + tempProvider.getUserID() + ": " + DatabaseController.getProvider(tempProvider.getUserID()).getName() + "? Y/N");
            			if(scan.nextLine().toLowerCase().equals("y")) {
            				System.out.println("Deleting Provider...");
                    		DatabaseController.deleteProvider(tempProvider.getUserID());
                    }
                    else {
                        System.out.println("Provider deletion aborted.");
                    }
    				}
    				else {
    					System.out.println("That is not a valid provider ID;");
    					deleteProvider();
    				}
    			}
        
    			catch(NumberFormatException e) {
    				System.out.println("Please enter a valid ID");
    				deleteProvider();
    			}		
		}
    }
}


