package demo.application.backend.model;

public class HourStatus {

	private int temperature;	// 15 C
	private String status;		// Clear
	private float windSpeed;	// 7.4 km/h
	private String hourOfDay;	// 23:00
	
	public HourStatus(int temperature, String status, float windSpeed, String hourOfDay) {
		super();
		this.temperature = temperature;
		this.status = status;
		this.windSpeed = windSpeed;
		this.hourOfDay = hourOfDay;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "HourStatus [temperature=" + temperature + ", status=" + status + ", windSpeed=" + windSpeed
				+ ", hourOfDay=" + hourOfDay + "]";
	}
	
}
