package persist;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import core.Reader;

public class ReaderCSV extends Reader{
	private ArrayList<String[]> myFile;
	private String myFilePath;
	
	
	@Override
	public void readCsv(ArrayList<String[]> myFile, String myFilePath) throws Exception {
		CSVReader reader = new CSVReader(new FileReader(myFilePath));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null){	
			myFile.add(nextLine);
			}
		reader.close();
	}
	public ArrayList<String[]> getMyFile() {
		return myFile;
	}
	public void setMyFile(ArrayList<String[]> myFile) {
		this.myFile = myFile;
	}
	public String getMyFilePath() {
		return myFilePath;
	}
	public void setMyFilePath(String myFilePath) {
		this.myFilePath = myFilePath;
	}
	

}
