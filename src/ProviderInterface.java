
import java.util.Scanner;

public class ProviderInterface {
    public boolean ProviderValidated = false;
    public boolean MemberValidated = false;


    Scanner scan = new Scanner(System.in);

    public void MainMenu() {

        int input = 0;
        System.out.println("\nMenu: \n1. Give Service\n2. Create Service Record\n3. View Provider Directory\n4. Log out\n");

        input = scan.nextInt();

        if (input == 1) {
            GiveService();
        } else if (input == 2) {
            CreateServiceRecord();
        } else if (input == 3) {
            ServiceCode temp = new ServiceCode();
            temp.DisplayServiceFile();
            MainMenu();
        } else if (input == 4) {
            ProviderTerminal temp = new ProviderTerminal();
            temp.start();
        } else {
            System.out.println("Sorry, that is an invalid request. Please try again.");
            MainMenu();
        }


    }

    public void GiveService() {
        System.out.println("Please enter your client's member number: ");
        int MemberNumber = scan.nextInt();
        Member temp = DatabaseController.getMember(MemberNumber);
        if (temp.getUserID() != -1) {
            if (temp.isSuspended) {
                System.out.println("Sorry, this member is suspended and cannot receive service.");
                MainMenu();
            } else {
                System.out.println("When you are finished, type Y to create the service record, or N to wait until later to create it.");
                char input2 = TryAgain();
                if (input2 == 'Y' || input2 == 'y') {
                    ServiceRecord newServiceRecord = new ServiceRecord();
                    newServiceRecord.GenerateServiceRecord();
                } else {
                    System.out.println("\nYou may create a service record at any time in the main menu.");
                    MainMenu();
                }
            }
        } else {
            System.out.println("\nSorry, that member doesn't seem to exist. Try again? (Y/N)");
            char tempx = TryAgain();
            if (tempx == 'y' || tempx == 'Y') {
                GiveService();
            }//try again
            else {
                MainMenu();
            }
        }
    }


    public void CreateServiceRecord() {//creates a service record object, does GenerateServiceRecord from servicerecord class
        ServiceRecord x = new ServiceRecord();
        x.GenerateServiceRecord();
    }


    public char TryAgain() {
        //System.out.println("\nWould you like to try again? (Y/N)");
        char input = scan.nextLine().charAt(0);
        while (input != 'y' && input != 'Y' && input != 'n' && input != 'N') {
            System.out.println("\nThat is not a valid input. Please try again.");
            input = scan.nextLine().charAt(0);
        }
        return input;
    }
}