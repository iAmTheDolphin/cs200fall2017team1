//need to make for past week
//name filePath based on current date & time

public class SummaryReport extends Report {
    private int provServices;
    private double provFees;
    private int totalProviders = 0;
    private int totalServices = 0;
    private double totalFees;
    private ServiceRecord[] recordDB;
    private Provider[] providerList;

    public SummaryReport() {
        filePath = "SummaryReports\\SummaryReport";
    }

/*
    protected void writeToFile() {
        String text = "";
        totalFees = 0.0;
        for (Provider provider : providerList) {
            provServices = 0;
            provFees = 0.0;
            for (ServiceRecord record : recordDB) {
                provServices++;
                ServiceCode service = DatabaseController.searchServiceCodes(record.Service.serviceName);
                provFees += service.serviceFee;
            }
            totalProviders++;
            totalServices += provServices;
            totalFees += provFees;
            text += "Provider Name:" + provider.getName() + '\n'
                    + "Number of Consultations This Week: " + provServices + '\n'
                    + "Total Fees This Week: " + provFees + '\n'
                    + '\n';
        }

        text += '\n'
                + "Number of Providers Who Provided Services: " + totalProviders + '\n'
                + "Total Number of Consultations This Week: " + totalServices + '\n'
                + "Total Fees to be Paid This Week: " + totalFees + '\n';

        reportText.write(text);
    }
    */
}
