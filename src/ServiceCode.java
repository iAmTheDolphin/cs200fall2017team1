

public class ServiceCode {
    String serviceName;
    int serviceCode;
    double serviceFee;

    public ServiceCode() {

    }

    public ServiceCode(String name, int code, double fee) {
        serviceName = name;
        serviceCode = code;
        serviceFee = fee;
    }

    boolean AddService(String name, int code, double fee){
        //add stuff to the file. if the file doesnt exist, it creates it. just a basic text file.
        //should there be different files for each provider? nah
    return false;}

    int FindServiceCode(String name) {
        //reads in the file if it exists, turning into vector 2 by x
        //searches array for name
        //returns code if found, if not returns }; //finds the code associated with this name
    return 0;}
    double FindServiceFee(String name){
        return 0;
    } //finds the fee associated with this name
    double FindServiceFee(int code){
        return 0;
    } //finds the fee associated with this code
    String FindServiceName(int code){
        return "0";
    } //finds the service name associated with this code
    void DisplayServiceFile(){} //this just opens up the file in a new window in case the user wants to just. look at em

}