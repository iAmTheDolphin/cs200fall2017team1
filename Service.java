

public class Service {
    String ServiceName;
    int ServiceCode;
    double ServiceFee;

    boolean AddService(string name, int code, double fee){
        //add stuff to the file. if the file doesnt exist, it creates it. just a basic text file.
        //should there be different files for each provider?
    }

    int FindServiceCode(string name){}; //finds the code associated with this name
    double FindServiceFee(string name){}; //finds the fee associated with this name
    double FindServiceFee(int code){}; //finds the fee associated with this code
    String FindServiceName(int code){}; //finds the service name associated with this code
    void DisplayServiceFile(){}; //this just opens up the file in a new window in case the user wants to just. look at em

};