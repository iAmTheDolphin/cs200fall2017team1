

public class ServiceRecord {//this can only be accessed by the provider interface, probably

    void GenerateServiceRecord(){}; //prompts user for into, then generates file with this information
    //the file is named ServiceRecord-providernumber-hh-ss-mm-dd-yyyy, to avoid naming errors
    void OpenServiceRecord(){}; //prompts user to enter into and searches for a file with that name in a pop-up window
    // if file isnt found, user can try again or choose to exit.


}