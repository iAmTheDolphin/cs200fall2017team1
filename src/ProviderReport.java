
public class ProviderReport extends Report {
	private Provider provider;
	
	public ProviderReport(Provider provider) {
		this.provider = provider;
		filePath = "ProviderReports\\" + provider.getUserID();
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
			
			//text+= "Total Number of Consulations: ";
			//text+= "Total Fee for Week: ";
		
			reportText.write(text);
		}
}
