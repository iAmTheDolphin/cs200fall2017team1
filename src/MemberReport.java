import java.util.Date;

/**
 * This generates a Member Report and determines what information to write to the report
 * @author Riley Manning
 */

/**
 * Declares and initializes necessary variables
 */

public class MemberReport extends Report {
	private Member member;
	ServiceRecord[] recordDB;
	private final long ONE_DAY = 1000*60*60*24;
	Date checkDate = new Date(System.currentTimeMillis()-(7*ONE_DAY));
	Date servDate = new Date();
	
	/**
	 * Accesses correct member/member info
	 * @param member is the member class for the requested report
	 */
	
	public MemberReport (Member member) {
		this.member = member;
		filePath = "MemberReports\\" + member.getUserID();
		recordDB = DatabaseController.searchServiceRecords(member);
	}
	
	/**
	 * Finds information to write to file
	 */
	@Override
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
		System.out.println(text);
		//reportText.write(text);
	}
	
}

