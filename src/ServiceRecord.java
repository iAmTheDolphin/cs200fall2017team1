

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
    Date currentDate;


    public void GenerateServiceRecord() { //prompts user for into, then generates file with this information
        //the file is named SR-providernumber-membernumber-date, to avoid naming errors
        //this assumes that a member can only recieve one service from a specific provider a day

        System.out.println("\nPlease enter the provider number: ");
        ProviderNumber = ValidateProvider();
        System.out.println("\nPlease enter the member number: ");
        MemberNumber = ValidateMember();

        ProviderName = DatabaseController.getProvider(ProviderNumber).getName();
        MemberName = DatabaseController.getMember(MemberNumber).getName();

        System.out.println("\nPlease enter the service name, or enter \"code\" if you want to search by code: ");
        ServiceName = scan.nextLine();
        ServiceCode y = new ServiceCode();
        if (ServiceName.equals("code")) {
            System.out.println("\nPlease enter the service code: ");
            int code = Integer.parseInt(scan.nextLine());
            y = FindServiceInfo(code, false);
        } else {
            y = FindServiceInfo(ServiceName, false);
        }

        System.out.println("\nPlease enter the date and time of the service in mm/dd/yyyy hh-mm-ss format: ");//using date, may not need this
        ServiceTime = scan.nextLine(); //this will be in the file, but the name of the file will contain the current date stamp.
        System.out.println("\nPlease enter any notes you would like to add: ");
        Notes = scan.nextLine();

        currentDate = new Date();//add stuff needed for this
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

    public ServiceCode FindServiceInfo(String ServiceName, boolean check) {
        ServiceCode y = DatabaseController.searchServiceCodes(ServiceName);
        if (check){
            if (y.serviceName == "-1"){
                System.out.println("\nThis isn't a valid name. Try again? (Y/N)");
                ProviderInterface tempx = new ProviderInterface();
                char input = tempx.TryAgain();
                if (input == 'y' || input == 'Y'){
                    System.out.println("\nIf you would like to search by code, enter Y. If you want to try searching by name, enter N.");
                    input = tempx.TryAgain();
                        if (input == 'y' || input == 'Y'){
                            System.out.println("Please enter the code.");
                            int temp = scan.nextInt();
                            FindServiceInfo(temp, true);
                        }
                        else {
                            System.out.println("\nPlease try re-entering the name.");
                            String temp = scan.nextLine();
                            FindServiceInfo(temp, true);
                        }
                }
                else {
                    System.out.println("You cannot make a service record without this information. Returning to main menu.");
                    ProviderInterface temp  = new ProviderInterface();
                    temp.MainMenu();
                }
            }
            if (y.serviceFee == -1){System.out.println("\nThis should not happen. If it does, fix later.");}
        }

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
                y = DatabaseController.searchServiceCodes(ServiceName);
            } else {
                System.out.println("\nPlease try entering the name again: ");
                String tempp = scan.nextLine();
                FindServiceInfo(tempp, true);
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
            FindServiceInfo(tempp, false);
        }

        return y;

    }

    public ServiceCode FindServiceInfo(int ServiceCode, boolean check) {
        ServiceCode y = DatabaseController.searchServiceCodes(ServiceCode);
        if (check) {
            if (y.serviceFee == -1) {
                System.out.println("\nThis service does not exist. Try again? (Y/N)");
                ProviderInterface tempx = new ProviderInterface();
                char input = tempx.TryAgain();
                if (input == 'y' || input == 'Y') {
                    System.out.println("\nIf you would like to search by code, enter Y. If you want to try searching by name, enter N.");
                    input = tempx.TryAgain();
                    if (input == 'y' || input == 'Y') {
                        System.out.println("Please enter the code.");
                        int temp = scan.nextInt();
                        FindServiceInfo(temp, true);
                    } else {
                        System.out.println("\nPlease try re-entering the name.");
                        String temp = scan.nextLine();
                        FindServiceInfo(temp, true);
                    }
                }
            }
        }
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
                y = temp;
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
        if (name == "-1"){
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

}