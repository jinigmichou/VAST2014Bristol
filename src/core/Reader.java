package core;

import java.util.ArrayList;
import persist.ReaderCSV;

/**
 * This class permits to read a csv file and put its content into an Arraylist<String[]>
 * @author jacquez
 *
 */
public class Reader{
	private ArrayList<String[]> myFile;
	private String myFilePath;
	private ReaderCSV myReader;
	
	//Constructors
	public Reader(){}
	
	public Reader(String myFilePath){
		this.myFilePath = myFilePath;
	}
	
	public Reader(ArrayList<String[]> myFile, String myFilePath, ReaderCSV myReader){
		this.myFile=myFile;
		this.myFilePath=myFilePath;
		this.myReader= myReader;
	}
	
	
	/**
	 * Function which calls readCsv of persist.ReaderCSV
	 * @param myFilePath
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]>  readCsv(String myFilePath) throws Exception{
		ReaderCSV myReader= new ReaderCSV(this.getMyFilePath());
		return myReader.readCsv(myFilePath);
	}
	
	// Getters and Setters
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
