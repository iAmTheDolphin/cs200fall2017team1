import java.util.Date;

/**
 * This generates a Provider Report and determines what information should be written to the report
 * @author Riley Manning
 */

public class ProviderReport extends Report {
    private Provider provider;
    private int numServices;
    private double totalFees;
    ServiceRecord[] recordDB;
    private long ONE_DAY = 1000*60*60*24;
	Date checkDate = new Date(System.currentTimeMillis()-(7*ONE_DAY));
	Date servDate = new Date();

	
	/**
	 * Assigns provider and determines file path
	 * @param provider passes in the provider whose report should be generated
	 */
	
    public ProviderReport(Provider provider) {
        this.provider = provider;
        filePath = "ProviderReports/" + provider.getUserID();
        recordDB = DatabaseController.searchServiceRecords(provider);
    }

    /**
     * Finds information to write to file
     */
    @Override
    protected void writeToFile() {
        String text = "Provider Name: " + provider.getName() + '\n';
        text += "Provider ID: " + provider.getUserID() + " " + '\n'
                + "Provider Address: " + provider.getStreetAddress() + '\n'
                + "Provider City: " + provider.getCity() + '\n'
                + "Provider State: " + provider.getState() + '\n'
                + "Provider Zip Code: " + provider.getZipCode() + '\n';
        totalFees = 0.0;
        for (ServiceRecord record : recordDB) {
            numServices++;
            ServiceCode service = DatabaseController.searchServiceCodes(record.Service.serviceName);
            totalFees += service.serviceFee;
			servDate = record.ServiceTime;
			if (servDate.after(checkDate)) {
	            text += "Date of Service: " + record.ServiceTime + '\n'
	                    + "Date and Time of Entry: " + '\n'
	                    + "Member Name: " + record.MemberName + '\n'
	                    + "Member Number: " + record.MemberNumber + '\n'
	                    + "Service Code: " + service.serviceCode + '\n'
	                    + "Service Fee: " + service.serviceFee + '\n'
	                    + '\n';
			}
        }
        text += "Total Number of Consultations: " + numServices + '\n';
        text += "Total Fees for Week: " + totalFees + '\n';
		//System.out.println(text);
        reportText.write(text);
    }
   
}
