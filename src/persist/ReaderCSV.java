package persist;

import java.io.FileReader;

import java.util.ArrayList;

import com.opencsv.CSVReader;

import core.Reader;

/**
 * This class permits to read a csv file and put its content into an Arraylist of String array
 * @author jacquez
 *
 */
public class ReaderCSV extends Reader{
	
	private ArrayList<String[]> myFile;
	private String myFilePath;
	
	public ReaderCSV(String myFilePath) {
		this.myFilePath= myFilePath;
	}
	
	//This method permits to read a CSV file and put it into an Arraylist<String[]>
	@Override
	public ArrayList<String[]> readCsv(String myFilePath) throws Exception {
		
		CSVReader reader = new CSVReader(new FileReader(getMyFilePath()));
		ArrayList<String[]> myTab=new ArrayList<String[]>();
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null){	
			myTab.add(nextLine);
			}
		reader.close();
		return myTab ;
	}
	
	//Getters and Setters
	
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
