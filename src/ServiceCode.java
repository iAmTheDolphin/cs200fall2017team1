

public class ServiceCode {
    public String serviceName;
    public int serviceCode;
    public double serviceFee;

    public ServiceCode() {
        serviceName = "-1";
        serviceCode = -1;
        serviceFee = -1;
    }

    public ServiceCode(String name, int code, double fee) {
        serviceName = name;
        serviceCode = code;
        serviceFee = fee;
    }

    public void AddService(){
        DatabaseController.addServiceCode(serviceName, serviceFee);
    }



}