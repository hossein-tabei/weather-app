package demo.application.backend.infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ApiCurrentStatus {

	private String name;		// city: Oakland
	private Sys sys;
	private List<WeatherItem> weather;
	private Main main;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Sys {
		private String country;		// US
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class WeatherItem {
		private Integer id;		// status: Clear
	}

	@Data
	public static class Main {
		private Double temp;		// temperature: 19 C
		private Double temp_min;	// minTemp: 15 C
		private Double temp_max;	// maxTemp: 25 C
		private Integer humidity;	// humidity: 35 %
		private Integer feels_like;	// realFeel:  14 C
	}
}
