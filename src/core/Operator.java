package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class Operator {
	private ArrayList<String[]> myFile;

	public Operator(){};

	public Operator(ArrayList<String[]> myFile){
		this.myFile=myFile;
	}


	/*public static int compare(String[] s1, String[] s2,int Column){
		if (s1[Column]==s2[Column])
			return 0;
		else if ( s1[Column].compareTo(s2[Column]) < 0)
			return 1;
		else
			return -1;
	}*/

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

	public static ArrayList<String[]> sortTimestamp(ArrayList<String[]> myFile, int columnDate){

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

			if (myFile.get(i)[1].equals(myFile.get(i+1)[1]) && !compareTimeStamp(myFile.get(i)[columnDate], myFile.get(i+1)[columnDate], 600000)){

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long timestamp = date.getTime();
			myFile.get(i)[columnDate] = Long.toString(timestamp);

		}
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
		else {
			return false;
		}

	}

	/**
	 * This function permits to generate an ArrayList<String[]> including distance, start/finish location
	 * @param myFile
	 * @return
	 */
	public  ArrayList<String[]> journeyCalculation(ArrayList<String[]> myFile){
		ArrayList<String[]> myFile2= new ArrayList<String[]>();
		String[] title ={"idCar","StartDate", "FinishDate","latStart","lonStart","latFinish","lonFinish", "Distance"};

		myFile2.add(title);
		for(int i=1;i<myFile.size();i++){
			String[] currentStamp= new String[8];
			currentStamp[0]=myFile.get(i)[0];
			//distance calculation
			currentStamp[7] = String.valueOf(distance(myFile.get(i)[2], myFile.get(i)[3], myFile.get(i)[5], myFile.get(i)[6], "N"));
			//start time
			currentStamp[1] = usingDateFormatter(Long.valueOf(myFile.get(i)[1]));
			//finish time
			currentStamp[2] = usingDateFormatter(Long.valueOf(myFile.get(i)[4]));
			//start location
			currentStamp[3] = myFile.get(i)[2];//lat
			currentStamp[4] = myFile.get(i)[3];//lon
			//finish location
			currentStamp[5] = myFile.get(i)[5];//lat
			currentStamp[6] = myFile.get(i)[6];//lon

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
	public double distance(String latStart, String lonStart, String latFinish, String lonFinish, String unit) {

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
	public double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}


	/**
	 * This function converts decimal degrees to radians
	 * @param deg
	 * @return
	 */
	public double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}


	/**
	 * function to convert timeStamp to date
	 * @param input is timestamp at Long format
	 * @return
	 */
	public String usingDateFormatter(long input){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar();
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);
	}
	/**
	 * Function to remove journey which distance is null or equals to NaN
	 * @param myFile
	 */
	public void verifyJourney(ArrayList<String[]> myFile){
		int j =0;
		for (int i=1;i<myFile.size();i++){
			if (myFile.get(i)[7].equals("0.0")||myFile.get(i)[7].equals("NaN")){
				myFile.remove(i);
				j++;
			}

		}
		System.out.println(j);
	}



	public ArrayList<String[]> getMyFile() {
		return myFile;
	}

	public void setMyFile(ArrayList<String[]> myFile) {
		this.myFile = myFile;
	}

}
