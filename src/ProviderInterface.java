
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class ProviderInterface {
    public boolean ProviderValidated = false;
    public boolean MemberValidated = false;

    int ProviderNum;

    public ProviderInterface (int providerNum) {
        ProviderNum = providerNum;
    }

    public ProviderInterface () {ProviderNum = -1;}

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
        int MemberNumber = -1;

        System.out.println("Please enter your client's member number: ");
        try {
            MemberNumber = Integer.parseInt(scan.nextLine());
        }
        catch(NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }

        Member temp = DatabaseController.getMember(MemberNumber);
        if (temp.getUserID() != -1) {
            if (temp.isSuspended) {
                System.out.println("Sorry, this member is suspended and cannot receive service.");
            } else {

                int serviceCode = -1;
                System.out.println("Please enter in the Service Code");
                try{
                    serviceCode = Integer.parseInt(scan.nextLine());
                }
                catch (NumberFormatException e){
                    System.out.println("ERROR: INVALID INPUT");
                }
                if(serviceCode != -1) {

                    System.out.println("When was this service rendered?");

                    SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
                    System.out.println("Please enter Date in the form : \"Wed Oct 16 12:30:00 CEST 2013\"");
                    try{
                        Date date = parserSDF.parse(scan.nextLine());

                        System.out.println(date.toString());
                    }
                    catch (ParseException e) {
                        System.out.println("ERROR: Date format was incorrect. " + e);
                    }

                }
            }
        } else {
            System.out.println("\nSorry, that member doesn't seem to exist. Try again? (Y/N)");
            char tempx = TryAgain();
            if (tempx == 'y' || tempx == 'Y') {
                GiveService();
            }//try again

        }
    }


    //allows provider to create a service record after providing a service
    private void CreateServiceRecord() {//creates a service record object, does GenerateServiceRecord from servicerecord class
        ServiceRecord x = new ServiceRecord(ProviderNum);
        //insert function call in database controller that writes this in necessary files

    }


    //utility that checks to make sure input is valid when doing y/n
    public char TryAgain() {

        System.out.println(scan.nextLine());
        char input = scan.nextLine()
                .charAt(0);
        while (input != 'y' && input != 'Y' && input != 'n' && input != 'N') {
            System.out.println("\nThat is not a valid input. Please try again.");
            input = scan.nextLine().charAt(0);
        }
        return input;
    }


}