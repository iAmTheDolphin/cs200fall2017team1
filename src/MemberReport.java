//need to match up service record to id number

//will receive member as parameter

import java.io.File;
//import service record information

public class MemberReport(member) extends Report {
	//general information
	int memberID;
	String memberName;
	String memberAddress;
	String memberCity;
	String memberState;
	String memberZipCode;
	String currentProvider;
	
	//update information for member
	
	memberID = member.getUserID();	
	
	//add array of classes for serviceRecord
	ServiceRecord record = new ServiceRecord;
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
		for (record) {
			//get memberNumber
			text += "Service: " + ServiceName + '\n'
					//+ "Service Date: " + serviceDate + '\n'
					+ "Provider: " + ProviderName + '\n'
					+ '\n';
		}
		file.print(text);
	}
}
