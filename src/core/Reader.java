package core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class Reader{
	private ArrayList<String[]> myFile;
	private String myFilePath;
	
	public Reader(){
		
	}
	public Reader(ArrayList<String[]> myFile, String myFilePath){
		myFile=this.myFile;
		myFilePath=this.myFilePath;
	}
	
	public void readCsv() throws Exception{
		CSVReader reader = new CSVReader(new FileReader(myFilePath));
		ArrayList<String[]> myFile=new ArrayList<String[]>();
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null){	
			myFile.add(nextLine);
		}
		
		
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
