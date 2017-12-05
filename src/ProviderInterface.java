
import javax.xml.crypto.Data;
import java.util.Scanner;

public class ProviderInterface {
    public boolean ProviderValidated = false;
    public boolean MemberValidated = false;


    Scanner scan = new Scanner(System.in);

    //displays options for provider
    public void MainMenu() {

        int input = 0;
        System.out.println("\nMenu: \n1. Give Service\n2. Create Service Record\n3. View Provider Directory\n4. Log out\n");

        switch(scan.nextLine()) {
            case "1" : GiveService(); break;
            case "2" : CreateServiceRecord(); break;
            case "3" : viewServiceCodes(); break;
            case "4" : break;
            default: System.out.println("Invalid Input"); MainMenu(); break;
        }
    }

    //prints the service directory for viewing
    private void viewServiceCodes() {
        DatabaseController.displayServiceCodes();
        MainMenu();
    }

    //allows provider to give service
    private void GiveService() {
        System.out.println("Please enter your client's member number: ");
        int MemberNumber = scan.nextInt();
        Member temp = DatabaseController.getMember(MemberNumber);
        if (temp.getUserID() != -1) {
            if (temp.isSuspended) {
                System.out.println("Sorry, this member is suspended and cannot receive service.");
                MainMenu();
            } else {
                System.out.println("When you are finished, type Y to create the service record, or N to wait until later to create it.");
                char input2 = TryAgain();//something about this line is causing issues, i think.
                if (input2 == 'Y' || input2 == 'y') {
                    ServiceRecord newServiceRecord = new ServiceRecord();
                    //newServiceRecord.GenerateServiceRecord();
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

    //allows provider to create a service record after providing a service
    private void CreateServiceRecord() {//creates a service record object, does GenerateServiceRecord from servicerecord class
        ServiceRecord x = new ServiceRecord();//....lemme double check on this
        //insert function call in database controller that writes this in necessary files

    }

    //utility that checks to make sure input is valid when doing y/n
    public char TryAgain() {
        char input = scan.nextLine().charAt(0);
        while (input != 'y' && input != 'Y' && input != 'n' && input != 'N') {
            System.out.println("\nThat is not a valid input. Please try again.");
            input = scan.nextLine().charAt(0);
        }
        return input;
    }


}