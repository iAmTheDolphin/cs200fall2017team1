import java.io.File;
//import service record information

public class MemberReport extends Report {
	//general information
	int memberID;
	String memberName;
	String memberAddress;
	String memberCity;
	String memberState;
	String memberZipCode;
	String currentProvider;
	
	//update information for member
	
	memberID = getUserID();
	memberName = getName();
	memberAddress = getStreetAddress();
	memberCity = getCity();
	memberState = get State();
	memberZipCode = getZipCode();
	
	
	//add array of classes for serviceRecord
	ServiceRecord record = new ServiceRecord;
	//record[0] = new MemberServiceRecord();
	
	//create file
	File file = new File("MemberReport" + memberID + ".txt");
	
	//writes to file
	void writeFile() {
		String text = "Member Name: " + memberName + '\n';
		text += "Member ID: " + memberID + " " + '\n'
				+ "Member Address: " + memberAddress + '\n'
				+ "Member City: " + memberCity + '\n'
				+ "Member State: " + memberState + '\n'
				+ "Member Zip Code: " + memberZipCode + '\n';
		for (record) {
			//get memberNumber
			text += "Service: " + ServiceName + '\n'
					+ "Service Date: " + serviceDate + '\n'
					+ "Provider: " + ProviderName + '\n';
		}
		file.print(string);
	}
}
