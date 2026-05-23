package demo.application.backend.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HourForecast {

	private int temperature;	// 15 C
	private int statusId;		// 800: clear sky
	private float windSpeed;	// 7.4 km/h
	private String hourOfDay;	// 23:00
}
