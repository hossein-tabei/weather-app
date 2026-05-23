package demo.application.backend.app;

import demo.application.backend.app.dto.DTOResultWrapper;
import demo.application.backend.app.dto.WeatherDto;
import demo.application.backend.weather.model.*;
import demo.application.backend.weather.repository.ForecastRepository;
import demo.application.backend.weather.repository.ForecastRepositoryImpl;
import demo.application.backend.weather.repository.LocationRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController {

	private static final String SUCCESSFUL_MESSAGE = "Operation Done Successfully";
	private final ForecastRepository forecastRepository;
	private final WeatherMapper weatherMapper;

	private final LocationRepositoryImpl geoService;
	private final ForecastRepositoryImpl forecastService;

	@GetMapping(path="/geo/search", produces= {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<Location>> searchGeo(@RequestParam String searchClause) {
		List<Location> searchResult = this.geoService.searchLocation(searchClause);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, searchResult);
	}

	@GetMapping(path = "/weather", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<WeatherDto> getWeatherByLocation(@RequestParam float lat, @RequestParam  float lon) {
		Weather weather = forecastRepository.getWeatherByLocation(lat, lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, weatherMapper.map(weather));
	}
	// -----------------------------------------------------------------



	@GetMapping(path = "/forecast/now", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<CurrentWeather> currentStatus(@RequestParam float lat, @RequestParam  float lon) {
		CurrentWeather fetchResult = forecastService.currentStatus(lat, lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, fetchResult);
	}

	@GetMapping(path = "/forecast/next5days", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<DayForecast>> next5DaysForecast(@RequestParam float lat, @RequestParam  float lon) {
		List<DayForecast> fetchResult = forecastService.next5DaysForecast(lat, lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, fetchResult);
	}

	@GetMapping(path = "/forecast/next24hours", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<HourForecast>> next24HoursForecast(@RequestParam float lat, @RequestParam  float lon) {
		List<HourForecast> fetchResult = forecastService.next24HoursForecast(lat, lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, fetchResult);
	}

	@GetMapping(path = "/forecast/aqi", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<AirQualityIndex> airQualityIndex(@RequestParam float lat, @RequestParam  float lon) {
		AirQualityIndex fetchResult = forecastService.airQualityIndex(lat, lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, fetchResult);
	}
}

