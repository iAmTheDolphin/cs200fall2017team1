import java.io.*;

public abstract class Report {
	PrintWriter reportText;
	String filePath;
	
	//create file
	File file = new File(".\\files\\" + filePath + ".txt");
	/*
	public void writeReport(String reportData) throws IOException {
		file.createNewFile();
		reportText = new PrintWriter(file);
		writeToFile();
		reportText.close();
	}
	*/
	
	//protected abstract void writeToFile();
}