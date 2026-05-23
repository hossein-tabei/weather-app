package demo.application.backend.weather.repository;

import demo.application.backend.weather.model.*;

import java.util.List;

public interface ForecastRepository {

	Weather getWeatherByLocation(float lat, float lon);

	CurrentWeather currentStatus(float lat, float lon);
	
	List<DayForecast> next5DaysForecast(float lat, float lon);
	
	List<HourForecast> next24HoursForecast(float lat, float lon);
	
	AirQualityIndex airQualityIndex(float lat, float lon);
}
