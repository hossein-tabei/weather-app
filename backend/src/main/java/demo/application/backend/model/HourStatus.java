package demo.application.backend.model;

public class HourStatus {

	private int temperature;	// 15 C
	private int statusId;		// 800: clear sky
	private float windSpeed;	// 7.4 km/h
	private String hourOfDay;	// 23:00
	
	public HourStatus(int temperature, int statusId, float windSpeed, String hourOfDay) {
		super();
		this.temperature = temperature;
		this.statusId = statusId;
		this.windSpeed = windSpeed;
		this.hourOfDay = hourOfDay;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(String hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	@Override
	public String toString() {
		return "HourStatus [temperature=" + temperature + ", statusId=" + statusId + ", windSpeed=" + windSpeed
				+ ", hourOfDay=" + hourOfDay + "]";
	}
	
}
