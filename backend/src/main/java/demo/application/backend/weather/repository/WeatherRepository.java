package demo.application.backend.weather.repository;

import demo.application.backend.weather.model.Weather;

public interface WeatherRepository {

	Weather getWeatherByLocation(float lat, float lon);
}
