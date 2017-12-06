package ChocAn;

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
	
	
	/**
	 * Writes data to a report file
	 */
	/*
	public void writeReport() {
		writeToFile();
	}*/
	
	public void writeReport() {
		File file = new File("./files/" + filePath + ".txt");
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
			reportText = new PrintWriter(file);
			writeToFile();
			reportText.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * Declaration of function (for compilation purposes)
	 */
	
	protected abstract void writeToFile();
}