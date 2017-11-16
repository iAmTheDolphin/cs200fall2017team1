import java.io.File;

public class MemberReport extends Report {
	//general information
	int memberID;
	String memberName;
	String memberAddress;
	String memberCity;
	String memberState;
	int memberZipCode;
	String currentProvider;
	
	//add array of classes for serviceRecord
	MemberServiceRecord[] record = new MemberServiceRecord[100];
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
			text += "Service: " + serviceProvided + '\n'
					+ "Service Date: " + serviceDate + '\n'
					+ "Provider: " + serviceProvider + '\n';
		}
		file.print(string);
	}
}
