package persist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

import core.Writer;

public class WriterCSV extends Writer {
	

	private ArrayList<String[]> myFile;
	private String myFilePath;
	
	public WriterCSV(){}
	
	public WriterCSV(ArrayList<String[]> myFile, String myFilePath){
		this.myFile=myFile;
		this.myFilePath=myFilePath;
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
	
	public static void writeCsv(ArrayList<String[]> myFile, String myFilePath) throws Exception {
		
		CSVWriter writer = new CSVWriter(new FileWriter(myFilePath), ',');
		
		for (int i=0;i<myFile.size();i++){
			writer.writeNext(myFile.get(i));

			writer.flushQuietly();

		}
		writer.close();
	}
	public static void writeFile(String myText, String myFilePath){
		 FileWriter fw= null;
	        File file =null;
	        try {
	            file=new File(myFilePath+".java");
	            if(!file.exists()) {
	                file.createNewFile();
	            }
	            fw = new FileWriter(file);
	            fw.write(myText);
	            fw.flush();
	            fw.close();
	            System.out.println("File written Succesfully");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}

}
