package persist;

import java.io.FileWriter;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

import core.Writer;

public class WriterCSV extends Writer {
	
	
	public void writeCsv(ArrayList<String[]> myFile, String myFilePath) throws Exception {
		
		CSVWriter writer = new CSVWriter(new FileWriter(myFilePath), ',');

		for (int i=0;i<myFile.size();i++){
			writer.writeNext(myFile.get(i));

			writer.flushQuietly();

		}
		writer.close();


	}

}
