package demo.application.backend.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentWeather {

	private String city;				// Oakland
	private String country;				// US
	private Integer statusId;			// 800: Clear
	private Double temperature;			// 19 C
	private Double minTemp;				// 15 C
	private Double maxTemp;				// 25 C
	private Integer humidity;			// 35 %
	private Integer realFeel;			// 14 C
	private Integer chanceOfRain = 10;	// 10 %
}
