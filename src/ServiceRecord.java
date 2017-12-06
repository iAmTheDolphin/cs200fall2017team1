import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.util.Calendar;


/**
 * This class generates service records and contains some utility functions to help the process.
 *
 * @author Aislinn
 * @version 1.0
 */

public class ServiceRecord {//this can only be accessed by the provider interface, probably

    Scanner scan = new Scanner(System.in);
    int ProviderNumber;
    int MemberNumber;
    String ProviderName;
    String MemberName;
    String Notes;
    Date ServiceTime;
    Date currentDate;
    ServiceCode Service;
    String all;

    /**
     * This generates a service record without prompting the user at all.
     */

    ServiceRecord(int providerNumber, int memberNumber, String providerName, String memberName, String notes, Date serviceTime, Date dateCreated, ServiceCode ser) {
        ProviderNumber = providerNumber;
        MemberNumber = memberNumber;
        Service = ser;
        currentDate = dateCreated;
        ServiceTime = serviceTime;
        Notes = notes;
        MemberName = memberName;
        ProviderName = providerName;
        all = ProviderName + " | " + ProviderNumber + " | " + MemberName + " | " + MemberNumber + " | " +
                Service.serviceName + " | " + Service.serviceCode + " | " + Service.serviceFee + " | " +
                ServiceTime + " | " + currentDate + " | " + Notes;
    }


    /**
     * This generates a service record by prompting the user.
     */

    public ServiceRecord(int providerNumber) { //prompts user for into, then generates file with this information
        int i = 1;
        while (i == 1) {

            ProviderNumber = providerNumber;

            //System.out.println("Please enter the member number: ");
            ProviderInterface temporary = new ProviderInterface();
            MemberNumber = temporary.ValidateMember();


            ProviderName = DatabaseController.getProvider(ProviderNumber).getName();
            MemberName = DatabaseController.getMember(MemberNumber).getName();

            Member member = DatabaseController.getMember(MemberNumber);


            if (member.getUserID() == -1) {
                i = 0;
                break;
            }

            System.out.println("\nNext you will enter the service information. Would you like to view the list of services first? (Y/N)");
            ProviderInterface x = new ProviderInterface();
            char input = x.TryAgain();
            if (input == 'y' || input == 'Y') {
                DatabaseController.displayServiceCodes();
            }
            int TempCode = -1;
            ServiceCode y = new ServiceCode();
            System.out.println("\nPlease enter the service code: ");
            try {
                TempCode = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer." + e);
            }
            y = DatabaseController.searchServiceCodes(TempCode);
            if (y.serviceFee == -1) {
                System.out.println("\nSorry, that is an invalid input.");
                i = 0;
                break;
            }
            Service = y;
            SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
            System.out.println("Please enter Date in the form : \"Wed Oct 16 12:30:00 CEST 2013\"");
            boolean check = false;
            try {
                ServiceTime = parserSDF.parse(scan.nextLine());

                System.out.println(parserSDF.format(ServiceTime));
            } catch (ParseException e) {
                System.out.println("ERROR: Date format was incorrect. " + e);
                check = true;
            }
            if (check) {
                i = 0;
                break;
            }//will set up a loop if time allows, but for now it just exits the thing
            System.out.println("\nPlease enter any notes you would like to add (anything more than 200 characters will be cut off): ");
            Notes = scan.nextLine();//change to read in next 200 characters

            currentDate = new Date();
            all = ProviderName + " | " + ProviderNumber + " | " + MemberName + " | " + MemberNumber + " | " +
                    Service.serviceName + " | " + Service.serviceCode + " | " + Service.serviceFee + " | " +
                    ServiceTime + " | " + currentDate + " | " + Notes + " ;";

            i = 0;

        }
    }


    /**
     * This creates a string for printing usage of a service record.
     *
     * @return String
     */

    public String toString() {

        return ProviderName + " : " + ProviderNumber + " \n" + MemberName + " : " + MemberNumber + " \n" +
                Service.serviceName + " : " + Service.serviceCode + " -->$" + Service.serviceFee + " \n Given at : " +
                ServiceTime + " \nCurrent Date is : " + currentDate + " \n" + Notes;

    }

    public String toFileString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        return ProviderName + " | " + ProviderNumber + " | " + MemberName +
                " | " + MemberNumber + " | " + Service.serviceName + " | " +
                Service.serviceCode + " | " + Service.serviceFee + " | " +
                formatter.format(ServiceTime) + " | " + formatter.format(currentDate) + " | " + Notes + " ;";
    }
}


