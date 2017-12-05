/**
 * This class holds ServiceCode objects and allows Service data to be found easily.
 *
 * @author Aislinn
 * @version 1.0
 */

public class ServiceCode {
    public String serviceName;
    public int serviceCode;
    public double serviceFee;

    /**
     * this generates a 'blank' service code object.
     */

    public ServiceCode() {
        serviceName = "-1";
        serviceCode = -1;
        serviceFee = -1;
    }

    /**
     * This generates a service code object with valid input.
     */

    public ServiceCode(String name, int code, double fee) {
        serviceName = name;
        serviceCode = code;
        serviceFee = fee;
    }

    public String toString() {
        return serviceName + " | " + serviceCode + " | " + serviceFee;
    }


/*
    public void DisplayServiceFile(){
        ServiceCode temp2 = new ServiceCode();
        int search = 100000;//this may be an issue. we shall see.
        boolean end = false;
        System.out.println("\n\nProvider Directory\n\n");
        while (!end) {
            temp2 = DatabaseController.searchServiceCodes(search);
            if (temp2.serviceCode == -1) {end = true; break;}
            else {
                System.out.println ("\nName: " + temp2.serviceName + " || ");
                System.out.println ("Code: " + temp2.serviceCode + " || ");
                System.out.println ("Fee: $" + temp2.serviceFee + " || ");
            }
        }

    } //this just prints the services*/

}