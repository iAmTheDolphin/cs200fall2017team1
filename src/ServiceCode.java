

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

    void AddService(){
        DatabaseController temp = new DatabaseController();
        temp.addServiceCode(serviceName, serviceCode, serviceFee);
    }

    void DisplayServiceFile(){} //this just opens up the file in a new window in case the user wants to just. look at em

}