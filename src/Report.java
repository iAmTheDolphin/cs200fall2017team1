import java.io.*;

/**
 * This create the file for the report and writes to the file
 * @author Riley Manning
 */

public abstract class Report {
	PrintWriter reportText;
	String filePath;
	
	/**
	 * Creates file and sets file path
	 */
	
	File file = new File(".\\files\\" + filePath + ".txt");
	
	/**
	 * @param reportData takes report info to be added
	 */
	
	public void writeReport(String reportData) throws IOException {
		file.createNewFile();
		reportText = new PrintWriter(file);
		writeToFile();
		reportText.close();
	}
	
	/**
	 * Declaration of function (for compilation purposes)
	 */
	
	protected abstract void writeToFile();
}