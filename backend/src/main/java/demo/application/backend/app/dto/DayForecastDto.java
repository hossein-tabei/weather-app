package demo.application.backend.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DayForecastDto {

	private String dayOfWeek;
	private String date;
	private int minStatusId;	// 800: clear sky
	private int maxStatusId;	// 804: overcast clouds
	private double minTemp;		// 15 C
	private double maxTemp;		// 25 C
	private double windSpeed;	// 7.4 km/h
}
