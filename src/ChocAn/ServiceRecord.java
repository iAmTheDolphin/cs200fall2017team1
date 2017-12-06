package ChocAn;

import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;


/**
 * This class generates service records and contains some utility functions to help the process.
 *
 * @author Aislinn
 * @version 1.0
 */

public class ServiceRecord {

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
     *
     * @param providerNumber the provider's userID
     * @param dateCreated the date the service record was created
     * @param memberName the name of the member
     * @param memberNumber the member's userID
     * @param notes any notes the provider added
     * @param providerName the name of the provider
     * @param ser the service provided
     * @param serviceTime the date the service was provided
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
     *
     * @param providerNumber the provider's userID
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
            System.out.println("\nEnter Y to use the current time, or N to enter a different time of service.");
            ProviderInterface z = new ProviderInterface();
            char input2 = z.TryAgain();
            boolean d = false;
            if (input2 == 'y' || input2 == 'Y') d = true;
            else{
            System.out.println("\nPlease enter Date in the form : \"Wed Oct 16 12:30:00 CEST 2013\"");
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
            }}
            System.out.println("\nPlease enter any notes you would like to add (anything more than 200 characters will be cut off): ");
            String str = scan.nextLine();//change to read in next 200 characters
            char[] charArray = new char[200];
            for (int j = 0; i < 200; i++)
                charArray[i]=str.charAt(i);
            Notes = new String(charArray);



            currentDate = new Date();
            if (d) ServiceTime = currentDate;
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

    /**
     * This creates a string for file printing usage of a service record.
     *
     * @return String
     */

    public String toFileString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        return ProviderName + " | " + ProviderNumber + " | " + MemberName +
                " | " + MemberNumber + " | " + Service.serviceName + " | " +
                Service.serviceCode + " | " + Service.serviceFee + " | " +
                formatter.format(ServiceTime) + " | " + formatter.format(currentDate) + " | " + Notes + " ;";
    }
}


