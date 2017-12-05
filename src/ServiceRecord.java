

import java.util.Scanner;
import java.util.Calendar;

public class ServiceRecord {//this can only be accessed by the provider interface, probably

    Scanner scan = new Scanner(System.in);
    int ProviderNumber;
    int MemberNumber;
    String ProviderName;
    String MemberName;
    String Notes;
    String ServiceTime;
    Calendar currentDate;
    ServiceCode Service;
    String all;

    ServiceRecord(int providerNumber, int memberNumber, String providerName, String memberName, String  notes, String serviceTime, Calendar time, ServiceCode ser){

        ProviderNumber = providerNumber;
        MemberNumber = memberNumber;
        Service = ser;
        currentDate = time;
        ServiceTime = serviceTime;
        Notes = notes;
        MemberName = memberName;
        ProviderName = providerName;

        //make all = all of the above, separated by |.
        all = ProviderName + " | " + ProviderNumber + " | " + MemberName + " | " + MemberNumber + " | " +
                Service.serviceName + " | " + Service.serviceCode + " | " + Service.serviceFee + " | " +
                ServiceTime + " | " + currentDate + " | " + Notes; //its so large oops
    }

    public ServiceRecord() { //prompts user for into, then generates file with this information
        //the file is named SR-providernumber-membernumber-date, to avoid naming errors
        //this assumes that a member can only recieve one service from a specific provider a day

        System.out.println("\nPlease enter the provider number: ");
        ProviderNumber = ValidateProvider();
        System.out.println("\nPlease enter the member number: ");
        MemberNumber = ValidateMember();

        String ProviderName = DatabaseController.getProvider(ProviderNumber).getName();
        String MemberName = DatabaseController.getMember(MemberNumber).getName();

        System.out.println("\nPlease enter the service name, or enter \"code\" if you want to search by code: ");
        String ServiceName = scan.nextLine();
        ServiceCode y = new ServiceCode();
        if (ServiceName.equals("code")) {
            System.out.println("\nPlease enter the service code: ");
            int code = Integer.parseInt(scan.nextLine());
            y = ServiceRecordUtility.FindServiceInfo(code, false);
        } else {
            y = ServiceRecordUtility.FindServiceInfo(ServiceName, false);
        }

        Service = y;

        System.out.println("\nPlease enter the date and time of the service in mm/dd/yyyy hh-mm-ss format: ");//using date, may not need this
        ServiceTime = scan.nextLine(); //do a check to make sure this is valid input
        System.out.println("\nPlease enter any notes you would like to add (anything more than 200 characters will be cut off): ");
        Notes = scan.nextLine();//change to read in next 200 characters
        currentDate = Calendar.getInstance();//add stuff needed for this

        all = ProviderName + " | " + ProviderNumber + " | " + MemberName + " | " + MemberNumber + " | " +
                Service.serviceName + " | " + Service.serviceCode + " | " + Service.serviceFee + " | " +
                ServiceTime + " | " + currentDate + " | " + Notes;
    }

    public int ValidateMember(){
        int ID = Integer.parseInt(scan.nextLine());
        String name = DatabaseController.getMember(ID).getName();
        if (name.equals("-1")){
            System.out.println("\nSorry, that member number is invalid. Would you like to try again?");
            ProviderInterface temp = new ProviderInterface();
            char response = temp.TryAgain();
            if (response == 'y' || response == 'Y'){
                    ValidateMember();
            }
            else {
                System.out.println("\nSorry, you cannot make a service record without this. Returning to main menu.");
                ProviderTerminal x = new ProviderTerminal();
                x.start();
            }
        }
        return ID;
    }

    public int ValidateProvider(){
        int ID = Integer.parseInt(scan.nextLine());
        String name = DatabaseController.getProvider(ID).getName();
        if (name.equals("-1")){
            System.out.println("\nSorry, that provider number is invalid. Would you like to try again?");
            ProviderInterface temp = new ProviderInterface();
            char response = temp.TryAgain();
            if (response == 'y' || response == 'Y'){
                ValidateProvider();
            }
            else {
                System.out.println("\nSorry, you cannot make a service record without this. Returning to main menu.");
                ProviderTerminal x = new ProviderTerminal();
                x.start();
            }
        }
        return ID;
    }

    public String toString() {

        return ProviderName + " : " + ProviderNumber + " \n" + MemberName + " : " + MemberNumber + " \n" +
                Service.serviceName + " : " + Service.serviceCode + " -->$" + Service.serviceFee + " \n Given at : " +
                ServiceTime + " \nCurrent Date is : " + currentDate + " \n" + Notes;

    }
}