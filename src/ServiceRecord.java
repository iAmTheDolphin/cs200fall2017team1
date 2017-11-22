

public class ServiceRecord {//this can only be accessed by the provider interface, probably

    Scanner scan = new Scanner (System.in);
    int ProviderNumber;
    int MemberNumber;
    String ProviderName;
    String MemberName;
    String ServiceName;
    String Notes;
    String ServiceTime;


    void GenerateServiceRecord() { //prompts user for into, then generates file with this information
        //the file is named SR-providernumber-membernumber-date, to avoid naming errors
        //this assumes that a member can only recieve one service from a specific provider a day

    System.out.println("\nPlease enter the provider number: ");
    ProviderNumber = scan.nextInt();
    System.out.println("\nPlease enter the member number: ");
    MemberNumber = scan.nextInt();
    System.out.println("\nPlease enter the provider name: ");
    ProviderName = scan.nextString();
    System.out.println("\nPlease enter the member name: ");
    MemberName = scan.nextString();
    System.out.println("\nPlease enter the service name: ");
    ServiceName = scan.nextString();
    DatabaseController x = new DatabaseController();
    ServiceCode y = x.searchServiceCodes(ServiceName);
    if (y.serviceFee == -1){
        System.out.println("\nIs this a new service? (Y/N)");
        char input = scan.nextChar();
        if (input == 'y' || input == 'Y'){
            int fee = 0;

            System.out.println("\nWhat is the fee for ", ServiceName, "?");
            fee = scan.nextDouble();

            ServiceCode temp = new ServiceCode(ServiceName, 0, fee);
            temp.AddService();
        }
    }

    System.out.println("\nThe fee for this service is : $", y.serviceFee, ". Is this correct?(Y/N)")//add a loop here

    System.out.println("\nPlease enter the date and time of the service in mm/dd/yyyy hh-mm-ss format: ");//using date, may not need this
    ServiceTime = scan.nextString(); //this will be in the file, but the name of the file will contain the current date stamp.
    System.out.println("\nPlease enter any notes you would like to add: ");
    Notes = scan.nextString();

        Date currentDate = GregorianCalendar.getInstance().getTime();//add stuff needed for this
        String output = new DateTime( currentDate ).toString("yyyyMMddHHmmss");//add stuff needed for this
    String filename = ProviderNumber.toString()+MemberNumber.toString()+output;//is this correct? lets find out
    PrintWriter writer = new PrintWriter(filename, "UTF-8");

    writer.println("Service Record\n");
        writer.println("\nDate and time of service: ", ServiceTime);
    writer.println("Provider Name: ", ProviderName);
        writer.println("\nProvider Number: ", ProviderNumber);
        writer.println("\nMember Name: ", MemberName);
        writer.println("\nMember Number: ", MemberNumber);
        writer.println("\n Service Given: "y.serviceName);
        writer.println("\n Service Code: "y.serviceCode);
        writer.println("\n Service Fee: $"y.serviceFee);
        writer.println("\nNotes: ", Notes);
        writer.println("\n");
        writer.close();


    }
    void OpenServiceRecord(){} //prompts user to enter into and searches for a file with that name in a pop-up window
    // if file isnt found, user can try again or choose to exit.
    //do we even need this?


}