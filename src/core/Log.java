package core;

import java.util.ArrayList;

import persist.WriterCSV;

public class Log {

	private ArrayList<String[]> myFile;
	private String myFilePath;
	private WriterCSV myWriter;
	private String string;

	public Log(){

	}

	public Log(ArrayList<String[]> myFile, String myFilePath, WriterCSV myWriter){
		this.myFile=myFile;
		this.myFilePath=myFilePath;
		this.myWriter=myWriter;
	}

	public String LogString (String msg){
		
		this.string = msg;	
		return string;
	}
}
