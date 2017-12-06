package ChocAn;

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
     *
     * @param code the service's code
     * @param fee  the service's fee
     * @param name the service's name
     */

    public ServiceCode(String name, int code, double fee) {
        serviceName = name;
        serviceCode = code;
        serviceFee = fee;
    }

    /**
     * This converts service code data into a readable string
     *
     * @return String
     */
    public String toString() {
        return serviceName + " | " + serviceCode + " | " + serviceFee;
    }
}


