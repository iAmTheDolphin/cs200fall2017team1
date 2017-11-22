

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
        DatabaseController temp = new DatabaseController();
        temp.addServiceCode(serviceName, serviceFee);
    }

    public void DisplayServiceFile(){
        DatabaseController temp = new DatabaseController();
        ServiceCode temp2 = new ServiceCode();
        int search = 100000;//this may be an issue. we shall see.
        boolean end = false;
        System.out.println("\n\nProvider Directory\n\n");
        while (!end) {
            temp2 = temp.searchServiceCodes(search);
            if (temp2.serviceCode == -1) {end = true; break;}
            else {
                System.out.println ("\nName: ", temp2.serviceName, " || ");
                System.out.println ("Code: ", temp2.serviceCode, " || ");
                System.out.println ("Fee: $", temp2.serviceFee, " || ");
            }
        }
        ProviderInterface finished = new ProviderInterface();
        finished.MainMenu();
    } //this just prints the services

}