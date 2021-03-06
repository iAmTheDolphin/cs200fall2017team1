package ChocAn;

import java.util.Scanner;

/**
 * This class holds most of the functionality for what Providers can do.
 *
 * @author Aislinn
 * @version 1.0
 */

public class ProviderInterface {
    public boolean ProviderValidated = false;
    public boolean MemberValidated = false;

    int ProviderNum;

    public ProviderInterface(int providerNum) {
        ProviderNum = providerNum;
    }

    public ProviderInterface() {
        ProviderNum = -1;
    }

    Scanner scan = new Scanner(System.in);

    /**
     * This displays all provider's options.
     *
     * @param providerNumber the provider's userID
     */
    public void MainMenu(int providerNumber) {
        ProviderNum = providerNumber;
        int input = 0;
        System.out.println("\nMenu: \n1. Give Service\n2. Create Service Record\n3. View Provider Directory\n4. Add Service to Directory\n5. Generate Provider Reoport\n\"quit\" to Log out");
        boolean logout = false;
        switch (scan.nextLine()) {
            case "1":
                GiveService();
                break;
            case "2":
                CreateServiceRecord();
                break;
            case "3":
                viewServiceCodes();
                break;
            case "4":
                AddService();
                break;
            case "5":
                generateProviderReport();
                break;
            case "quit":
                logout = true;
                break;
            default:
                System.out.println("Invalid Input");

                break;
        }
        if (!logout) MainMenu(ProviderNum);
    }

    /**
     * This lets someone add a service.
     */
    public void AddService() {
        String serviceName;
        double serviceFee = 0.0;

        System.out.println("\nWhat is the name of the service?");
        serviceName = scan.nextLine();
        System.out.println("What is the fee of the service?");
        try {
            serviceFee = Double.parseDouble(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: incorrect input\n" + e);
        }

        DatabaseController.addServiceCode(serviceName, serviceFee);

    }


    /**
     * This displays all of the service codes.
     */

    private void viewServiceCodes() {
        DatabaseController.displayServiceCodes();

    }

    /**
     * This allows the provider to give a service.
     */
    private void GiveService() {
        int MemberNumber = -1;

        MemberNumber = ValidateMember();
        Member temp = DatabaseController.getMember(MemberNumber);
        int i = 1;
        if (temp.getUserID() == -1) i = 0;
        while (i == 1) {
            if (temp.isSuspended) {
                System.out.println("Sorry, this member is suspended and cannot receive service.");
                i = 0;
            } else {
                System.out.println("\nNow give the service. When done, enter 'Y' to create a service record, or 'N' to wait until later.");
                char input = TryAgain();
                if (input == 'Y' || input == 'y') {
                    CreateServiceRecord();
                    i = 0;
                }
            }
        }
    }


    /**
     * This allows the provider to create a service record.
     */
    private void CreateServiceRecord() {//creates a service record object, does GenerateServiceRecord from servicerecord class
        ServiceRecord x = new ServiceRecord(ProviderNum);
        DatabaseController.addServiceRecord(x);
        System.out.println("\nService record has been created!");

    }


    /**
     * This checks for valid y/n input.
     *
     * @return char
     */
    public char TryAgain() {

        //System.out.println(scan.nextLine());
        char input = scan.nextLine()
                .charAt(0);
        while (input != 'y' && input != 'Y' && input != 'n' && input != 'N') {
            System.out.println("\nThat is not a valid input. Please try again.");
            input = scan.nextLine().charAt(0);
        }
        return input;
    }


    /**
     * This validates a member.
     *
     * @return int
     */
    public int ValidateMember() {
        System.out.println("Please enter your client's member number: ");
        int ID = 0;
        try {
            ID = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        Member temp = DatabaseController.getMember(ID);
        if (temp.getUserID() == -1) {
            System.out.println("\nSorry, that member number is invalid. Would you like to try again?");
            char response = TryAgain();
            if (response == 'y' || response == 'Y') {
                ValidateMember();
            } else {
                System.out.println("\nSorry, returning to main menu.");
            }
        }
        return ID;
    }


    /**
     * Generates provider report
     */
    public void generateProviderReport() {

        try {
            Provider tempProvider = DatabaseController.getProvider(ProviderNum);
            if (tempProvider.getUserID() != -1) {
                System.out.println("Generate Provider Report for " + DatabaseController.getProvider(tempProvider.getUserID()).getName() + "? Y/N");
                if (scan.nextLine().toLowerCase().equals("y")) {
                    System.out.println("Generating Provider Report...");
                    ProviderReport providerReport = new ProviderReport(tempProvider);
                    providerReport.writeReport();
                } else {
                    System.out.println("Report generation aborted.");
                }
            } else {
                System.out.println("That is not a valid provider ID;");
                generateProviderReport();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid ID");
            generateProviderReport();
        }
    }

}