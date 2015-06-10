package core;

import java.util.ArrayList;

public abstract class Writer {
	private ArrayList<String[]> myFile;
	private String myFilePath;
	
	public Writer(){
		
	}
	public Writer(ArrayList<String[]> myFile, String myFilePath){
		myFile=this.myFile;
		myFilePath=this.myFilePath;
	}
	
	public abstract void writeCsv(ArrayList<String[]> myFile, String myFilePath) throws Exception;
		
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
