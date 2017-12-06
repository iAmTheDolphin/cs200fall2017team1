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

    ServiceRecord(int providerNumber, int memberNumber, String providerName, String memberName, String notes, Date serviceTime, ServiceCode ser) {
        ProviderNumber = providerNumber;
        MemberNumber = memberNumber;
        Service = ser;
        currentDate = new Date();
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

        do{

        ProviderNumber = providerNumber;

        //System.out.println("Please enter the member number: ");
        ProviderInterface temporary = new ProviderInterface();
        MemberNumber = temporary.ValidateMember();


        String ProviderName = DatabaseController.getProvider(ProviderNumber).getName();
        String MemberName = DatabaseController.getMember(MemberNumber).getName();

        if (MemberName == "-1") break;

        System.out.println("\nNext you will enter the service information. Would you like to view the list of services first? (Y/N)");
        ProviderInterface x = new ProviderInterface();
        char input = x.TryAgain();
        if (input == 'y'|| input == 'Y'){
            DatabaseController.displayServiceCodes();
        }
        int TempCode = -1;
        ServiceCode y = new ServiceCode();
        System.out.println("\nPlease enter the service code: ");
        try{
            TempCode = Integer.parseInt(scan.nextLine());
            }
        catch(NumberFormatException e){
            System.out.println("Please enter a valid integer." +e);}

        y = DatabaseController.searchServiceCodes(TempCode);

        if (y.serviceFee == -1) {
            System.out.println("\nSorry, that is an invalid input."); break;}

        Service.serviceFee = y.serviceFee;
        Service.serviceCode = y.serviceCode;
        Service.serviceName = y.serviceName;

        SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
                    System.out.println("Please enter Date in the form : \"Wed Oct 16 12:30:00 CEST 2013\"");
                    try{
                        ServiceTime = parserSDF.parse(scan.nextLine());

                        System.out.println(parserSDF.format(ServiceTime));
                    }
                    catch (ParseException e) {
                        System.out.println("ERROR: Date format was incorrect. " + e);
                    }
        System.out.println("\nPlease enter any notes you would like to add (anything more than 200 characters will be cut off): ");
        Notes = scan.nextLine();//change to read in next 200 characters
        Date currentDate = new Date();

        all = ProviderName + " | " + ProviderNumber + " | " + MemberName + " | " + MemberNumber + " | " +
                Service.serviceName + " | " + Service.serviceCode + " | " + Service.serviceFee + " | " +
                ServiceTime + " | " + currentDate + " | " + Notes;

    } while (1==1);}



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
}


