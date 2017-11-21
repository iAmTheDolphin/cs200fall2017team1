

public class ServiceCode {
    String ServiceName;
    int ServiceCode;
    double ServiceFee;


    boolean AddService(string name, int code, double fee){
        //add stuff to the file. if the file doesnt exist, it creates it. just a basic text file.
        //should there be different files for each provider? nah
    }

    int FindServiceCode(string name){
        //reads in the file if it exists, turning into vector 2 by x
        //searches array for name
        //returns code if found, if not returns 0

    }; //finds the code associated with this name
    double FindServiceFee(string name){
        ImportServices();
    }; //finds the fee associated with this name
    double FindServiceFee(int code){}; //finds the fee associated with this code
    String FindServiceName(int code){}; //finds the service name associated with this code
    void DisplayServiceFile(){}; //this just opens up the file in a new window in case the user wants to just. look at em

};