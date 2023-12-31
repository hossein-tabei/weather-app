package demo.application.backend.model;

public class CurrentStatus {

	private String city;		// Oakland
	private String country;		// US
	private int statusId;		// 800: Clear
	private double temperature;	// 19 C
	private double minTemp;		// 15 C
	private double maxTemp;		// 25 C
	private int humidity;		// 35 %
	private int realFeel;		// 14 C
	private int chanceOfRain;	// 10 %
	
	public CurrentStatus(String city, String country, int statusId, double temperature, double minTemp, double maxTemp,
			int humidity, int realFeel, int chanceOfRain) {
		
		this.city = city;
		this.country = country;
		this.statusId = statusId;
		this.temperature = temperature;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.humidity = humidity;
		this.realFeel = realFeel;
		this.chanceOfRain = chanceOfRain;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
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

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getRealFeel() {
		return realFeel;
	}

	public void setRealFeel(int realFeel) {
		this.realFeel = realFeel;
	}

	public int getChanceOfRain() {
		return chanceOfRain;
	}

	public void setChanceOfRain(int chanceOfRain) {
		this.chanceOfRain = chanceOfRain;
	}

	@Override
	public String toString() {
		return "CurrentStatus [city=" + city + ", country=" + country + ", statusId=" + statusId + ", temperature="
				+ temperature + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", humidity=" + humidity
				+ ", realFeel=" + realFeel + ", chanceOfRain=" + chanceOfRain + "]";
	}
	
}
