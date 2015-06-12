package persist;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import core.Reader;

public class ReaderCSV extends Reader{
	private ArrayList<String[]> myFile;
	private String myFilePath;
	
	public ReaderCSV(String myFilePath) {
		myFilePath= this.myFilePath;
	}
	@Override
	public ArrayList<String[]> readCsv(String myFilePath) throws Exception {
		System.out.println("essaie4"+ getMyFilePath());
		CSVReader reader = new CSVReader(new FileReader(getMyFilePath()));
		ArrayList<String[]> myTab=new ArrayList<String[]>();
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null){	
			myTab.add(nextLine);
			}
		reader.close();
		return myTab ;
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
