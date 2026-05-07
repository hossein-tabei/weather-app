package demo.application.backend.forecast.service;

import demo.application.backend.model.AirQualityIndex;
import demo.application.backend.model.CurrentStatus;
import demo.application.backend.model.DayStatus;
import demo.application.backend.model.HourStatus;

import java.util.List;

public interface ForecastService {

	CurrentStatus currentStatus(float lat, float lon);
	
	List<DayStatus> next5DaysForecast(float lat, float lon);
	
	List<HourStatus> next24HoursForecast(float lat, float lon);
	
	AirQualityIndex airQualityIndex(float lat, float lon);
}
