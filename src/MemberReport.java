import java.util.Date;
/*
 * Riley Manning
 */

public class MemberReport extends Report {
	private Member member;
	ServiceRecord[] recordDB;
	private long ONE_DAY = 1000*60*60*24;
	Date checkDate = new Date(System.currentTimeMillis()-(7*ONE_DAY));
	Date servDate = new Date();
	
	
	//assigns correct member
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
			servDate = record.ServiceTime;
			if (servDate.after(checkDate)) {
				text += "Date of Service: " + record.ServiceTime + '\n'
						+ "Provider Name: " + record.ProviderName + '\n'
						+ "Service Name: " + record.Service.serviceName + '\n'
						+ '\n';
			}
		}
		reportText.write(text);
	}
	
}

