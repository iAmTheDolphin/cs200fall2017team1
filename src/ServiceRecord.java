

import java.util.Scanner;
import java.util.Date;

public class ServiceRecord {//this can only be accessed by the provider interface, probably

    Scanner scan = new Scanner(System.in);
    int ProviderNumber;
    int MemberNumber;
    String ProviderName;
    String MemberName;
    String ServiceName;
    String Notes;
    String ServiceTime;


    public void GenerateServiceRecord() { //prompts user for into, then generates file with this information
        //the file is named SR-providernumber-membernumber-date, to avoid naming errors
        //this assumes that a member can only recieve one service from a specific provider a day

        System.out.println("\nPlease enter the provider number: ");
        ProviderNumber = scan.nextInt();
        System.out.println("\nPlease enter the member number: ");
        MemberNumber = scan.nextInt();
        System.out.println("\nPlease enter the provider name: ");
        ProviderName = scan.nextLine();
        System.out.println("\nPlease enter the member name: ");
        MemberName = scan.nextLine();
        System.out.println("\nPlease enter the service name, or enter \"code\" if you want to search by code: ");
        ServiceName = scan.nextLine();
        ServiceCode y = new ServiceCode();
        if (ServiceName == "code") {
            System.out.println("\nPlease enter the service code: ");
            int code = scan.nextInt();
            y = FindServiceInfo(code);
        } else {
            y = FindServiceInfo(ServiceName);
        }

        System.out.println("\nPlease enter the date and time of the service in mm/dd/yyyy hh-mm-ss format: ");//using date, may not need this
        ServiceTime = scan.nextLine(); //this will be in the file, but the name of the file will contain the current date stamp.
        System.out.println("\nPlease enter any notes you would like to add: ");
        Notes = scan.nextLine();

        Date currentDate = new Date();//add stuff needed for this
        String filename = ProviderNumber + "-" + MemberNumber + "-" + currentDate.toString();//is this correct? lets find out
        /* TODO fix this writing to file. maybe in Database controller?
        PrintWriter writer = new PrintWriter(filename, "UTF-8");

        writer.println("Service Record\n");
        writer.println("\nDate and time of service: ", ServiceTime);
        writer.println("Provider Name: ", ProviderName);
        writer.println("\nProvider Number: ", ProviderNumber);
        writer.println("\nMember Name: ", MemberName);
        writer.println("\nMember Number: ", MemberNumber);
        writer.println("\n Service Given: ", y.serviceName);
        writer.println("\n Service Code: ", y.serviceCode);
        writer.println("\n Service Fee: $", y.serviceFee);
        writer.println("\nNotes: ", Notes);
        writer.println("\n");
        writer.close();

        ProviderInterface temp = new ProviderInterface();
        temp.MainMenu();
        */
    }

    public void OpenServiceRecord() {
    } //prompts user to enter into and searches for a file with that name in a pop-up window
    // if file isnt found, user can try again or choose to exit.
    //do we even need this?

    public ServiceCode FindServiceInfo(String ServiceName) {
        ServiceCode y = DatabaseController.searchServiceCodes(ServiceName);
        if (y.serviceFee == -1) {
            System.out.println("\nIs this a new service? (Y/N)");
            ProviderInterface tempx = new ProviderInterface();
            char input = tempx.TryAgain();
            if (input == 'y' || input == 'Y') {
                double fee = 0.0;

                System.out.println("\nWhat is the fee for " + ServiceName + "?");
                fee = scan.nextDouble();

                ServiceCode temp = new ServiceCode(ServiceName, 0, fee);
                temp.AddService();
            } else {
                System.out.println("\nPlease try entering the name again: ");
                String tempp = scan.nextLine();
                FindServiceInfo(tempp);
            }

        }
        System.out.println("\nThe fee for this service is : $" + y.serviceFee + ". Is this correct?(Y/N)");
        ProviderInterface tempy = new ProviderInterface();
        char input = tempy.TryAgain();
        if (input == 'y' || input == 'Y') {
            return y;
        } else {
            System.out.println("\nPlease try re-entering the service name. ");
            String tempp = scan.nextLine();
            FindServiceInfo(tempp);
        }

        return y;

    }

    public ServiceCode FindServiceInfo(int ServiceCode) {
        ServiceCode y = DatabaseController.searchServiceCodes(ServiceCode);
        if (y.serviceFee == -1) {
            System.out.println("\nIs this a new service? (Y/N)");
            ProviderInterface tempy = new ProviderInterface();
            char input = tempy.TryAgain();
            if (input == 'y' || input == 'Y') {
                System.out.println("\nKeep in mind that this new service may have a different code than the one you entered.");
                double fee = 0.0;
                String ServiceName;
                System.out.println("\nWhat is the name of this service? ");
                ServiceName = scan.nextLine();
                System.out.println("\nWhat is the fee for " + ServiceName + "?");
                fee = scan.nextDouble();

                ServiceCode temp = new ServiceCode(ServiceName, 0, fee);
                temp.AddService();
                return y;
            } else {
                System.out.println("\nPlease try entering the code again: ");
                int tempp = scan.nextInt();
                FindServiceInfo(tempp);
            }

        }
        System.out.println("\nThe fee for this service is : $" + y.serviceFee + ". Is this correct?(Y/N)");
        ProviderInterface tempy = new ProviderInterface();
        char input = tempy.TryAgain();
        if (input == 'y' || input == 'Y') {
            return y;
        } else {
            System.out.println("\nPlease try re-entering the service code. ");
            int tempp = scan.nextInt();
            FindServiceInfo(tempp);
        }
        System.out.println("\nThe name of this service is :" + y.serviceName + ". Is this correct? (Y/N)");
        input = tempy.TryAgain();
        if (input == 'y' || input == 'Y') {
            return y;
        } else {
            System.out.println("\nPlease try re-entering the service code. ");
            int tempp = scan.nextInt();
            FindServiceInfo(tempp);
        }
        return y;


    }

}