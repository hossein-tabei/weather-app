package demo.application.backend.model;

public class DayStatus {

	private String dayOfWeek;
	private String date;
	private String minStatus;	// Clear
	private String maxStatus;	// Clear
	private int minTemp;		// 15 C
	private int maxTemp;		// 25 C
	private float windSpeed;	// 7.4 km/h
	
	public DayStatus(String dayOfWeek, String date, String minStatus, String maxStatus, int minTemp, int maxTemp,
			float windSpeed) {
		
		super();
		this.dayOfWeek = dayOfWeek;
		this.date = date;
		this.minStatus = minStatus;
		this.maxStatus = maxStatus;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.windSpeed = windSpeed;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMinStatus() {
		return minStatus;
	}
	public void setMinStatus(String minStatus) {
		this.minStatus = minStatus;
	}
	public String getMaxStatus() {
		return maxStatus;
	}
	public void setMaxStatus(String maxStatus) {
		this.maxStatus = maxStatus;
	}
	public int getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}
	public int getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}
	public float getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}
	@Override
	public String toString() {
		return "DayStatus [dayOfWeek=" + dayOfWeek + ", date=" + date + ", minStatus=" + minStatus + ", maxStatus="
				+ maxStatus + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", windSpeed=" + windSpeed + "]";
	}
	
}
