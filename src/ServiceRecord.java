

public class ServiceRecord {//this can only be accessed by the provider interface, probably

    int ProviderNumber;
    int MemberNumber;
    String ProviderName;
    String MemberName;
    int ServiceCode;
    double ServiceFee;
    String ServiceName;
    Date current;
    String Notes;


    void GenerateServiceRecord() { //prompts user for into, then generates file with this information
        //the file is named SR-providernumber-membernumber-date, to avoid naming errors
        //this assumes that a member can only recieve one service from a specific provider a day

    System.out.println("\nPlease enter the provider number: ");
    System.out.println("\nPlease enter the member number: ");
    System.out.println("\nPlease enter the provider name: ");
    System.out.println("\nPlease enter the member name: ");
    System.out.println("\nPlease enter the service name: ");
    DatabaseController x = new DatabaseController();
    ServiceCode y = x.searchServiceCodes(ServiceName);
    y.ServiceFee= ServiceFee;
    y.ServiceCode= ServiceCode;
    System.out.println("\nPlease enter the date in mm/dd/yyyy format: ");//using date, may not need this
    System.out.println("\nPlease enter the time in hh-mm-ss format: ");//using date, may not need this
    System.out.println("\nPlease enter any notes you would like to add: ");



    }
    void OpenServiceRecord(){} //prompts user to enter into and searches for a file with that name in a pop-up window
    // if file isnt found, user can try again or choose to exit.


}