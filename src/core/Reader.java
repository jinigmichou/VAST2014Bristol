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
	public Reader( String myFilePath){
		
		myFilePath=this.myFilePath;

	}
	public Reader(ArrayList<String[]> myFile, String myFilePath, ReaderCSV myReader){
		myFile=this.myFile;
		myFilePath=this.myFilePath;
		myReader= this.myReader;
	}

	public ArrayList<String[]>  readCsv(String myFilePath) throws Exception{
		System.out.println("essaie3"+ getMyFilePath());
		ReaderCSV myReader= new ReaderCSV(this.getMyFilePath());
		return myReader.readCsv(myFilePath);
	}

	public static int compare(String[] s1, String[] s2,int Column){
		if (s1[Column]==s2[Column])
			return 0;
		else if ( s1[Column].compareTo(s2[Column]) < 0)
			return 1;
		else
			return -1;
	}

	/**
	 * This function sorts an ArrayList<String[]> by lexicographical order
	 * @param myFile
	 * @param Column it is the reference column for  sorting
	 */
	public void sortColumn(ArrayList<String[]> myFile, final int Column){
		Collections.sort(myFile, new Comparator<String[]>(){
			@Override
			public int compare(String[] s1, String[] s2) {

				if (s1[Column].equals(s2[Column]))
					return 0;
				else if ( s1[Column].compareTo( s2[Column]) < 0)
					return -1;
				else
					return 1;
			}

		});
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
