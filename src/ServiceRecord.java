

public class ServiceRecord {//this can only be accessed by the provider interface, probably

    int ProviderNumber;
    int MemberNumber;
    String ProviderName;
    String MemberName;
    int ServiceCode;
    double ServiceFee;
    String ServiceName;


    void GenerateServiceRecord() { //prompts user for into, then generates file with this information
        //the file is named ServiceRecord-providernumber-Membernumber-mm-dd-yyyy, to avoid naming errors
        //this assumes that a member can only recieve one service from a provider a day
        int month=0;
        int day=0;
        int year=0;



    }
    void OpenServiceRecord(){} //prompts user to enter into and searches for a file with that name in a pop-up window
    // if file isnt found, user can try again or choose to exit.


}