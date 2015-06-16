package core;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import persist.ReaderCSV;



public class Reader{
	private ArrayList<String[]> myFile;
	private String myFilePath;
	private ReaderCSV myReader;

	public Reader(){

	}
	public Reader(String myFilePath){
		
		this.myFilePath = myFilePath;

	}
	public Reader(ArrayList<String[]> myFile, String myFilePath, ReaderCSV myReader){
		this.myFile=myFile;
		this.myFilePath=myFilePath;
		this.myReader= myReader;
	}

	public ArrayList<String[]>  readCsv(String myFilePath) throws Exception{
		ReaderCSV myReader= new ReaderCSV(this.getMyFilePath());
		return myReader.readCsv(myFilePath);
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
