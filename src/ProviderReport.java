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
				ServiceCode service = DatabaseController.searchServiceCodes(record.ServiceName);
				totalFees += service.serviceFee;
				text += "Date of Service: " + record.ServiceTime + '\n'
						+ "Provider Name: " + record.ProviderName + '\n'
						+ "Service Name: " + record.ServiceName + '\n'
						+ '\n';
			}
			text+= "Total Number of Consultations: " + numServices + '\n';
			text+= "Total Fees for Week: " + totalFees + '\n';
		
			reportText.write(text);
		}
}
