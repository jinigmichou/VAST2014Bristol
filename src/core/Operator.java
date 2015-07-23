package core;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
/**
 * This class regroups all basic operations about csv files in a form of ArrayList<String[]>
 * @author jacquez
 *
 */
public class Operator {

	public Operator(){};


	/**
	 * This function sorts an ArrayList<String[]> by lexicographical order
	 * @param myFile
	 * @param Column it is the reference column for  sorting
	 */
	public static void sortColumn(ArrayList<String[]> myFile, final int Column){
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


	/**
	 * This method allows to gather by journeys GPS data with timestamp 
	 * User can choose the interval between two journeys 
	 * @param myFile
	 * @param columnDate
	 * @param timeInterval
	 * @return
	 */
	public static ArrayList<String[]> sortTimestamp(ArrayList<String[]> myFile, int columnDate, int timeInterval){

		ArrayList<String[]> myFile2= new ArrayList<String[]>();
		String[] title ={"idCar","StartTimeStamp","StartLat","StartLon", "FinishTimestamp", "FinishLat", "FinishLon"};
		String[] stamp= new String[7];

		myFile2.add(title);
		int i = 0;

		boolean boo=false;
		while (i<myFile.size()-1){
			String[] currentStamp= new String[7];

			if(boo==false){

				currentStamp[0]=myFile.get(i)[1];
				currentStamp[1]=myFile.get(i)[0];
				currentStamp[2]=myFile.get(i)[2];
				currentStamp[3]=myFile.get(i)[3];
				boo=true;
			}
			else{
				currentStamp[0]=stamp[0];
				currentStamp[1]=stamp[1];
				currentStamp[2]=stamp[2];
				currentStamp[3]=stamp[3];
			}

			if (myFile.get(i)[1].equals(myFile.get(i+1)[1]) && !compareTimeStamp(myFile.get(i)[columnDate], myFile.get(i+1)[columnDate], timeInterval )){

				currentStamp[4]=myFile.get(i)[0];
				currentStamp[5]=myFile.get(i)[2];
				currentStamp[6]=myFile.get(i)[3];
				myFile2.add(currentStamp);

				boo=false;
			}
			stamp = currentStamp;
			i++;
		}
		return myFile2;
	}

	/**
	 * Function to compare timeStamp
	 * return True if time between the two timestamps is smaller than timeInterval in ms.
	 * Else return false
	 * @param s1 the first timestamp String format
	 * @param s2 the second timestamp String format
	 * @param timeInterval time minimum between two journeys in ms
	 * @return
	 */
	public static boolean compareTimeStamp(String s1, String s2, int timeInterval){

		long i1= Long.parseLong(s1);
		long i2= Long.parseLong(s2);

		if (i2-i1<timeInterval){
			return true;
		}
		else 
			return false;
	}


	/**
	 * function to convert date to timeStamp
	 * @param myFile is the file to update
	 * @param columnDate is the column number of column date
	 */
	public static void tranformDate(ArrayList<String[]> myFile, int columnDate){

		int i = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(i=0;i<myFile.size();i++){
			Date date = new Date();
			try {
				date = format.parse(myFile.get(i)[columnDate]);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			long timestamp = date.getTime();
			myFile.get(i)[columnDate] = Long.toString(timestamp);
		}
	}


	/**
	 * This function permits to generate an ArrayList<String[]> including distance, start/finish location
	 * @param myFile
	 * @return
	 */
	public static ArrayList<String[]> journeyCalculation(ArrayList<String[]> myFile, int start_time, int finish_time, int lat_start, int lon_start, int lat_finish, int lon_finish, String unit){

		ArrayList<String[]> myFile2= new ArrayList<String[]>();
		String[] title ={"idCar","StartDate", "FinishDate","latStart","lonStart","latFinish","lonFinish", "Distance"};

		myFile2.add(title);
		for(int i=1;i<myFile.size();i++){
			String[] currentStamp= new String[8];
			currentStamp[0]=myFile.get(i)[0];
			//distance calculation
			currentStamp[7] = String.valueOf(distance(myFile.get(i)[lat_start], myFile.get(i)[lon_start], myFile.get(i)[lat_finish], myFile.get(i)[lon_finish], unit));
			//start time
			currentStamp[1] = usingDateFormatter(Long.valueOf(myFile.get(i)[start_time]));
			//finish time
			currentStamp[2] = usingDateFormatter(Long.valueOf(myFile.get(i)[finish_time]));
			//start location
			currentStamp[3] = myFile.get(i)[lat_start];//lat
			currentStamp[4] = myFile.get(i)[lon_start];//lon
			//finish location
			currentStamp[5] = myFile.get(i)[lat_finish];//lat
			currentStamp[6] = myFile.get(i)[lon_finish];//lon

			myFile2.add(currentStamp);
		}
		return myFile2;
	}

	/**
	 * function to calculate distance between two points. One point i equivalent to a latitude point and a longitude point
	 * @param latStart latitude start degree decimal
	 * @param lonStart longitude start degree decimal
	 * @param latFinish latitude start degree decimal
	 * @param lonFinish longitude start degree decimal
	 * @param unit kind of unit, K for Km or N for mile
	 * @return
	 */
	public static double distance(String latStart, String lonStart, String latFinish, String lonFinish, String unit) {

		double lat1 = Double.parseDouble(latStart);
		double lat2 = Double.parseDouble(latFinish);
		double lon1 = Double.parseDouble(lonStart);
		double lon2 = Double.parseDouble(lonFinish);
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} 
		else if (unit == "N") {
			dist = dist * 0.8684;
		}
		return (dist);
	}


	/**
	 * This function converts radians to decimal degrees
	 * @param rad
	 * @return
	 */
	public static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}


	/**
	 * This function converts decimal degrees to radians
	 * @param deg
	 * @return
	 */
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/**
	 * Function to remove journey which distance is null or equals to NaN
	 * @param myFile
	 */
	public static ArrayList<String[]> verifyJourney(ArrayList<String[]> myFile){
		ArrayList<String[]> myFileError = new ArrayList<String[]>();
		myFileError.add(myFile.get(0));
		for (int i=1;i<myFile.size();i++){
			for(int j=0; j<myFile.get(0).length;j++){
				if (myFile.get(i)[j].equals("0.0")||myFile.get(i)[j].equals("NaN")){
					myFileError.add(myFile.get(i));
					myFile.remove(i);
				}
			}
		}
		return myFileError;
	}


	/**
	 * function to convert timeStamp to date
	 * @param input is timestamp at Long format
	 * @return
	 */
	public static String usingDateFormatter(long input){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar();
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);
	}


	/**
	 * This function allows to convert a date from long format to string format in a file
	 * @param myFile file to be edited
	 * @param columnDate column references column date
	 * @param dateFormat date format required
	 */
	public static void timeStampToDate(ArrayList<String[]> myFile, int columnDate, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
		for(int i = 1;i<myFile.size();i++){
			Date date = new Date( Long.parseLong(myFile.get(i)[columnDate]));
			Calendar cal = new GregorianCalendar();
			sdf.setCalendar(cal);
			cal.setTime(date);
			myFile.get(i)[columnDate] = sdf.format(date);
		}
	}


	/**
	 * This function allows to convert only a date from long format to string format
	 * @param myValue value to be edited
	 * @param dateFormat date format required
	 */
	public static void timeStampToDateForOneValue(String myValue, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date( Long.parseLong(myValue));
		Calendar cal = new GregorianCalendar();
		sdf.setCalendar(cal);
		cal.setTime(date);
		myValue = sdf.format(date);
	}

	/**
	 * This function allows to convert a date from String format to long format in a file
	 * @param myFile file to be edited
	 * @param columnDate column references column date
	 * @param dateFormat initial date format 
	 */
	public static void dateStringtoTimestamp(ArrayList<String[]> myFile, int columnDate, String dateFormat){
		int i = 0;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		for(i=1;i<myFile.size();i++){
			Date date = new Date();
			try {
				date = format.parse(myFile.get(i)[columnDate]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long timestamp = date.getTime();
			myFile.get(i)[columnDate] = Long.toString(timestamp);
		}
	}


	/**
	 * This function allows to convert only a date from string format to long format
	 * @param myValue value to be edited
	 * @param dateFormat initial date format 
	 * @return
	 */
	public static  String dateStringtoTimestampForOneValue(String myValue, String dateFormat){
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date date = new Date();
		try {
			date = format.parse(myValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long timestamp = date.getTime();
		return Long.toString(timestamp);
	}



	/**
	 * This method allows to select distinct values of a chosen column of a file
	 * @param myFile 
	 * @param column chosen column 
	 * @return
	 */
	public static ArrayList<String> selectDistinctValuesInAColumn(ArrayList<String[]> myFile, int column){
		ArrayList<String> tab = new ArrayList<String>();
		for (int i = 0 ; i<myFile.size() ; i++){
			if(!tab.contains(myFile.get(i)[column])){
				tab.add(myFile.get(i)[column]);
			}
		}
		return tab;
	}

	/**
	 * This method allows to merge two files according to two reference columns (for example columns ID permits to link two csv files)
	 * @param myFile1
	 * @param column1 reference column of file 1
	 * @param myFile2
	 * @param column2 reference column of file 2
	 * @return
	 */
	public static ArrayList<String[]> twoCsvFilesMerging(ArrayList<String[]> myFile1, int column1,ArrayList<String[]> myFile2, int column2){
		ArrayList<String[]> result = new ArrayList<String[]>();
		result.add(concat(myFile2.get(0),myFile1.get(0)));
		for(int i = 1; i<myFile2.size();i++){
			for(int j= 1; j<myFile1.size(); j++){
				if (myFile2.get(i)[column2].equals(myFile1.get(j)[column1])){
					result.add(concat(myFile2.get(i),myFile1.get(j)));
				}
			}
		}
		return result;
	}

	/**
	 * This method permits to concatenate two arrays of string
	 * @param a
	 * @param b
	 * @return
	 */
	public static String[] concat(String[] a, String[] b) {
		int aLen = a.length;
		int bLen = b.length;
		String[] c= new String[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}


	/**
	 * This methods allows to clone an Arraylist<String[]> in order to create a real copy and to prevent pointer issues
	 * @param myfile
	 * @return
	 */
	public static ArrayList<String[]> cloneArrayList(ArrayList<String[]> myfile){
		ArrayList<String[]> myfile2 = new ArrayList<String[]>(myfile.size()); 
		for(int i = 0 ; i< myfile.size(); i++){
			myfile2.add(myfile.get(i).clone());
		}
		return myfile2;
	}


	/**
	 * This method allows to delete a column of a csv file
	 * It is useful after a merging of two csv files because the new file has two same columns (for example ID column)
	 * @param myFile
	 * @param column
	 * @return
	 */
	public static ArrayList<String[]> deleteColumn(ArrayList<String[]> myFile, int column){
		ArrayList<String[]> myFileResult = new ArrayList<String[]>();
		for(int i = 0 ; i<myFile.size(); i++){
			String[] tabStamp = new String[myFile.get(0).length-1];
			int k = 0;
			for (int j = 0 ; j < myFile.get(i).length ; j++){
				if (j!=column){
					System.out.println("jojo "+j);
					tabStamp[k] = myFile.get(i)[j];
					k++;
				}
			}
			myFileResult.add(tabStamp);
		}
		return myFileResult;
	}

	/**
	 * This function allows to add an Arraylist<String[]> at the end of an other
	 * It is useful to gather a file with its error file after correction
	 * @param myFile1
	 * @param myFile2
	 * @return
	 */
	public static ArrayList<String[]> appendFileToAnOther(ArrayList<String[]> myFile1, ArrayList<String[]> myFile2){
		for(int i = 1 ; i<myFile2.size() ; i++ ){
			myFile1.add(myFile2.get(i));
		}
		return myFile1;
	}


	/**
	 * This method permits to select value in a file between two dates chosen
	 * @param myFile
	 * @param columnDay column date
	 * @param date1 start date
	 * @param date2 finish date
	 * @return
	 */
	public static ArrayList<String[]> selectValuesBetweenTwoTimestamps(ArrayList<String[]> myFile, int columnDay, String date1, String date2){

		ArrayList<String[]> myFileResult = new ArrayList<String[]>();
		for (int i = 1;  i< myFile.size() ; i++){
			if (Operator.compareTimeStamp(myFile.get(i)[columnDay], date1, 0)
					&& Operator.compareTimeStamp(date2, myFile.get(i)[columnDay], 0)){
				myFileResult.add(myFile.get(i));
			}
		}
		return myFileResult ;
	}

	/**
	 * This function permits to check if a value is contained within an array of string
	 * @param tab
	 * @param myValue
	 * @return
	 */
	public static boolean containInArray(String[] tab, String myValue){
		boolean result = false;
		for (int i= 0 ; i<tab.length ; i++){
			if (tab[i].equals(myValue)) result = true;
		}
		return result;
	}


	/**
	 * This method allows to trade two columns of an ArrayList<String[]>
	 * @param myFile
	 * @param column1
	 * @param column2
	 */
	public static void tradeTwoColumns(ArrayList<String[]> myFile, int column1, int column2){
		for(int i = 0 ;i<myFile.size(); i++){
			String stamp = new String ();
			stamp = myFile.get(i)[column1];
			myFile.get(i)[column1] = myFile.get(i)[column2];
			myFile.get(i)[column2] = stamp; 
		}
	}


	/**
	 * This method allows to select a file with a browser file
	 * @return
	 */
	public static String selectFile(){

		File file;
		File repertoireCourant = null;
		try {
			repertoireCourant = new File(".").getCanonicalFile();

		} catch(IOException e) {}

		JFileChooser dialogue = new JFileChooser(repertoireCourant);
		// display
		dialogue.showOpenDialog(null);
		file = dialogue.getSelectedFile();
		if (file !=null) {return file.toString();}
		return null;
	}


	/**
	 * This function permits to search files in a directory according to a pattern
	 * @param directory
	 * @param pattern
	 * @return
	 */
	public static ArrayList<String> directoryListWithPattern(File directory, String pattern){ 

		String [] fileList; 
		ArrayList<String> result  = new ArrayList<String>();
		int i; 
		fileList=directory.list(); 
		for(i=0;i<fileList.length;i++){ 
			if(fileList[i].endsWith(pattern)==true){ 
				result.add(fileList[i]);				
			}
		} 
		return result;
	}

}
