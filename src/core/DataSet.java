package core;

public class DataSet {
	
	private String timestamp ;
	private String id;
	private String latitude;
	private String longitude;
	private String path;
	private String interval;

	private static DataSet dataSet = null;

	public DataSet(){
	}
	
	public String getDataSetTimestamp() {
		return timestamp;
	}

	public void setDataSetTimestamp(String dataSetTimestamp) {
		this.timestamp = dataSetTimestamp;
	}
	
	public String getDataSetId() {
		return id;
	}

	public void setDataSetId(String dataSetId) {
		this.id = dataSetId;
	}
	
	public String getDataSetLatitude() {
		return latitude;
	}

	public void setDataSetLatitude(String dataSetLatitude) {
		this.latitude = dataSetLatitude;
	}
	
	public String getDataSetLongitude() {
		return longitude;
	}

	public void setDataSetLongitude(String dataSetLongitude) {
		this.longitude = dataSetLongitude;
	}
	
	public String getDataSetPath() {
		return path;
	}

	public void setDataSetPath(String dataSetPath) {
		this.path = dataSetPath;
	}
	
	public String getDataSetInterval() {
		return interval;
	}

	public void setDataSetInterval(String dataSetInterval) {
		this.interval = dataSetInterval;
	}
}
