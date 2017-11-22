//need to match up service record to id number

//will receive member as parameter

import java.io.File;
//import service record information

public class MemberReport {
	//general information
	private Member member;
	int memberID;
	String memberName;
	String memberAddress;
	String memberCity;
	String memberState;
	String memberZipCode;
	String currentProvider;

	public MemberReport (Member member) {
		this.member = member;
		memberID = member.getUserID();
		memberName = member.getName();
		memberAddress = member.getStreetAddress();
	}
	
	//update information for member
	

	
	//add array of classes for serviceRecord
	ServiceRecord record = new ServiceRecord();
	//record[0] = new MemberServiceRecord();
	
	//create file
	File file = new File("MemberReport" + memberID + ".txt");
	
	//writes to file
	void writeFile() {
		String text = "Member Name: " + member.getName() + '\n';
		text += "Member ID: " + memberID + " " + '\n'
				+ "Member Address: " + member.getStreetAddress() + '\n'
				+ "Member City: " + member.getCity() + '\n'
				+ "Member State: " + member.getState() + '\n'
				+ "Member Zip Code: " + member.getZipCode() + '\n';

	}
}
