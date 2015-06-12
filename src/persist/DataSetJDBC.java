package persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.relique.jdbc.csv.CsvDriver;

import core.DataSet;

public class DataSetJDBC extends DataSet {

	public DataSetJDBC(String[] args){

		try
		{
			// Create a connection. The first command line parameter is
			// the directory containing the .csv files.
			// A single connection is thread-safe for use by several threads.

			Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + args[0]);

			// Create a Statement object to execute the query with.
			// A Statement is not thread-safe.
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//Statement stmt = conn.createStatement();

			// Select the ID and NAME columns from sample.csv
			ResultSet results = stmt.executeQuery("SELECT id, timestamp, lat, long FROM gps ORDER BY id");

			// Dump out the results to a CSV file with the same format
			// using CsvJdbc helper function

			boolean append = true;
			CsvDriver.writeToCsv(results, System.out, append);

			// Clean up
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
