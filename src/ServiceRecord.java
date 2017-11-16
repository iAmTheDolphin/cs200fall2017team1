

public class ServiceRecord {//this can only be accessed by the provider interface, probably



    void GenerateServiceRecord() { //prompts user for into, then generates file with this information
        //the file is named ServiceRecord-providernumber-hh-ss-mm-dd-yyyy, to avoid naming errors
        int providernumber=0;
        int hour=0;
        int minute=0;
        int second=0;
        int month=0;
        int day=0;
        int year=0;
    };
    void OpenServiceRecord(){}; //prompts user to enter into and searches for a file with that name in a pop-up window
    // if file isnt found, user can try again or choose to exit.


}