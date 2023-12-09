package demo.application.backend.model;

public class CurrentStatus {

	private String city;		// Oakland
	private String country;		// US
	private String status;		// Clear
	private int temperature;	// 19 C
	private int minTemp;		// 15 C
	private int maxTemp;		// 25 C
	private int humidity;		// 35 %
	private int realFeel;		// 14 C
	private int chanceOfRain;	// 10 %
	private AirQualityIndex airQualityIndex;
	
	public CurrentStatus(String city, String country, String status, int temperature, int minTemp, int maxTemp,
			int humidity, int realFeel, int chanceOfRain, AirQualityIndex airQualityIndex) {
		
		this.city = city;
		this.country = country;
		this.status = status;
		this.temperature = temperature;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.humidity = humidity;
		this.realFeel = realFeel;
		this.chanceOfRain = chanceOfRain;
		this.airQualityIndex = airQualityIndex;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
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
	
	public AirQualityIndex getAirQualityIndex() {
		return airQualityIndex;
	}

	public void setAirQualityIndex(AirQualityIndex airQualityIndex) {
		this.airQualityIndex = airQualityIndex;
	}

	@Override
	public String toString() {
		return "CurrentStatus [city=" + city + ", country=" + country + ", status=" + status + ", temperature="
				+ temperature + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", humidity=" + humidity
				+ ", realFeel=" + realFeel + ", chanceOfRain=" + chanceOfRain + ", airQualityIndex=" + airQualityIndex
				+ "]";
	}
	
}
