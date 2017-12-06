//need to make for past week
//add date and time of entry

public class ProviderReport extends Report {
    private Provider provider;
    private int numServices;
    private double totalFees;
    ServiceRecord[] recordDB;

    public ProviderReport(Provider provider) {
        this.provider = provider;
        filePath = "ProviderReports\\" + provider.getUserID();
        recordDB = DatabaseController.searchServiceRecords(provider);
    }


    //writes to file
    protected void writeToFile() {
        String text = "Provider Name: " + provider.getName() + '\n';
        text += "Provider ID: " + provider.getUserID() + " " + '\n'
                + "Provider Address: " + provider.getStreetAddress() + '\n'
                + "Provider City: " + provider.getCity() + '\n'
                + "Provider State: " + provider.getState() + '\n'
                + "Provider Zip Code: " + provider.getZipCode() + '\n';
        //update services
        totalFees = 0.0;
        for (ServiceRecord record : recordDB) {
            numServices++;
            ServiceCode service = DatabaseController.searchServiceCodes(record.Service.serviceName);
            totalFees += service.serviceFee;
            text += "Date of Service: " + record.ServiceTime + '\n'
                    + "Date and Time of Entry: " + '\n'
                    + "Member Name: " + record.MemberName + '\n'
                    + "Member Number: " + record.MemberNumber + '\n'
                    + "Service Code: " + service.serviceCode + '\n'
                    + "Service Fee: " + service.serviceFee + '\n'
                    + '\n';
        }
        text += "Total Number of Consultations: " + numServices + '\n';
        text += "Total Fees for Week: " + totalFees + '\n';

        reportText.write(text);
    }

}
