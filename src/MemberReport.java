//need to match up service record to id number
//import service record information

public class MemberReport extends Report {
	private Member member;

	public MemberReport (Member member) {
		this.member = member;
		filePath = "MemberReports\\" + member.getUserID();
	}
	
	//add array of classes for serviceRecord
	//ServiceRecord record = new ServiceRecord();

	
	//writes to file
	protected void writeToFile() {
		String text = "Member Name: " + member.getName() + '\n';
		text += "Member ID: " + member.getUserID() + " " + '\n'
				+ "Member Address: " + member.getStreetAddress() + '\n'
				+ "Member City: " + member.getCity() + '\n'
				+ "Member State: " + member.getState() + '\n'
				+ "Member Zip Code: " + member.getZipCode() + '\n';
		reportText.write(text);
	}
}

