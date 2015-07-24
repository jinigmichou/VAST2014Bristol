package core;

import java.util.ArrayList;

import persist.WriterCSV;


/**
 * This class allows to write an ArrayList<String[]> into a CSV file
 * @author jacquez
 *
 */
public class Writer {
	private ArrayList<String[]> myFile;
	private String myFilePath;
	private WriterCSV myWriter;
	
	//Constructors
	public Writer(){
		this.myFile = new ArrayList<String[]>();
		this.myFilePath = null;
		this.myWriter = null;
	}
	
	public Writer(ArrayList<String[]> myFile, String myFilePath){
		this.myFile=myFile;
		this.myFilePath=myFilePath;	
		this.myWriter = null;
	}
	
	

	public Writer(ArrayList<String[]> myFile, String myFilePath, WriterCSV myWriter){
		this.myFile=myFile;
		this.myFilePath=myFilePath;
		this.myWriter=myWriter;
		
	}
	
	/**
	 * This method allows to write an Arraylist<String[]> into a csv file
	 * @param myFile
	 * @param myFilePath
	 * @throws Exception
	 */
	public static void writeCsv(ArrayList<String[]> myFile, String myFilePath) throws Exception{
		WriterCSV.writeCsv(myFile, myFilePath);
	}
	
	/**
	 * This method allows to write a text (String format) into a file
	 * @param myText
	 * @param myFilePath
	 * @throws Exception
	 */
	public static void writeFile(String myText, String myFilePath) throws Exception{
		WriterCSV.writeFile(myText, myFilePath);
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
	
	public WriterCSV getMyWriter() {
		return myWriter;
	}

	public void setMyWriter(WriterCSV myWriter) {
		this.myWriter = myWriter;
	}
}
