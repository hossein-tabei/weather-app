package demo.application.backend.model;

public class Location {

	private double lat;
	private double lon;
	private String city;
	private String state;
	private String country;
	
	public Location(double lat, double lon, String city, String state, String country) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Location [lat=" + lat + ", lon=" + lon + ", city=" + city + ", state=" + state + ", country=" + country
				+ "]";
	}
	
}
