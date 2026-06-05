package demo.application.backend.weather.repository;

import demo.application.backend.weather.model.Weather;

public interface WeatherRepository {

	Weather getWeatherByLocation(double lat, double lon);
}
