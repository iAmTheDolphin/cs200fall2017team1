//need to match up service record to id number
//import service record information

public class MemberReport extends Report {
	private Member member;
	ServiceRecord[] recordDB;

	public MemberReport (Member member) {
		this.member = member;
		filePath = "MemberReports\\" + member.getUserID();
		recordDB = DatabaseController.searchServiceRecords(member);
	}
	
	//writes to file
	protected void writeToFile() {
		String text = "Member Name: " + member.getName() + '\n';
		text += "Member ID: " + member.getUserID() + " " + '\n'
				+ "Member Address: " + member.getStreetAddress() + '\n'
				+ "Member City: " + member.getCity() + '\n'
				+ "Member State: " + member.getState() + '\n'
				+ "Member Zip Code: " + member.getZipCode() + '\n'
				+ '\n';
		
		for (ServiceRecord record : recordDB) {
			text += "Date of Service: " + record.ServiceTime + '\n'
					+ "Provider Name: " + record.ProviderName + '\n'
					+ "Service Name: " + record.ServiceName + '\n'
					+ '\n';
		}
		reportText.write(text);
	}
}

