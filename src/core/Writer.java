package core;

import java.util.ArrayList;

import persist.WriterCSV;



public class Writer {
	private ArrayList<String[]> myFile;
	private String myFilePath;
	private WriterCSV myWriter;
	
	public Writer(){
		
	}
	
	public Writer(ArrayList<String[]> myFile, String myFilePath){
		this.myFile=myFile;
		this.myFilePath=myFilePath;

		
	}
	public Writer(ArrayList<String[]> myFile, String myFilePath, WriterCSV myWriter){
		this.myFile=myFile;
		this.myFilePath=myFilePath;
		this.myWriter=myWriter;
		
	}
	
	public static void writeCsv(ArrayList<String[]> myFile, String myFilePath) throws Exception{

		WriterCSV.writeCsv(myFile, myFilePath);
	}
	public static void writeFile(String myText, String myFilePath) throws Exception{

		WriterCSV.writeFile(myText, myFilePath);
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
