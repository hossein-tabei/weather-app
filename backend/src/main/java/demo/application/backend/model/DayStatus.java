package demo.application.backend.model;

public class DayStatus {

	private String dayOfWeek;
	private String date;
	private int minStatusId;	// 800: clear sky
	private int maxStatusId;	// 804: overcast clouds
	private double minTemp;		// 15 C
	private double maxTemp;		// 25 C
	private double windSpeed;	// 7.4 km/h
	
	public DayStatus(String dayOfWeek, String date, int minStatusId, int maxStatusId, double minTemp, double maxTemp,
			double windSpeed) {
		
		super();
		this.dayOfWeek = dayOfWeek;
		this.date = date;
		this.minStatusId = minStatusId;
		this.maxStatusId = maxStatusId;
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
	public int getMinStatusId() {
		return minStatusId;
	}
	public void setMinStatusId(int minStatusId) {
		this.minStatusId = minStatusId;
	}
	public int getMaxStatusId() {
		return maxStatusId;
	}
	public void setMaxStatusId(int maxStatusId) {
		this.maxStatusId = maxStatusId;
	}
	public double getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}
	public double getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	@Override
	public String toString() {
		return "DayStatus [dayOfWeek=" + dayOfWeek + ", date=" + date + ", minStatusId=" + minStatusId + ", maxStatusId="
				+ maxStatusId + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", windSpeed=" + windSpeed + "]";
	}
	
}
