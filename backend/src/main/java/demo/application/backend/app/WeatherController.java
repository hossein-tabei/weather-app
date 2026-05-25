package demo.application.backend.app;

import demo.application.backend.app.dto.*;
import demo.application.backend.weather.LocationMapper;
import demo.application.backend.weather.model.Location;
import demo.application.backend.weather.repository.LocationRepository;
import demo.application.backend.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {
	private static final String SUCCESSFUL_MESSAGE = "Operation Done Successfully";

	private final WeatherMapper weatherMapper;
	private final WeatherRepository weatherRepository;

	private final LocationMapper locationMapper;
	private final LocationRepository locationRepository;

	@GetMapping(path="/location/search", produces= {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<LocationDto>> searchGeo(@RequestParam String searchClause) {
		List<Location> locations = locationRepository.searchLocation(searchClause);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, locationMapper.mapTo(locations));
	}

	@GetMapping(path = "/now", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<CurrentWeatherDto> currentStatus(@RequestParam float lat, @RequestParam  float lon) {
		WeatherDto weatherDto = getWeather(lat,  lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, weatherDto.getCurrentWeather());
	}

	@GetMapping(path = "/forecast/next5days", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<DayForecastDto>> next5DaysForecast(@RequestParam float lat, @RequestParam  float lon) {
		WeatherDto weatherDto = getWeather(lat,  lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, weatherDto.get_5DaysForecast());
	}

	@GetMapping(path = "/forecast/next24hours", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<HourForecastDto>> next24HoursForecast(@RequestParam float lat, @RequestParam  float lon) {
		WeatherDto weatherDto = getWeather(lat,  lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, weatherDto.get_24HoursForecast());
	}

	@GetMapping(path = "/aqi", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<AirQualityIndexDto> airQualityIndex(@RequestParam float lat, @RequestParam  float lon) {
		WeatherDto weatherDto = getWeather(lat,  lon);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, weatherDto.getAirQualityIndex());
	}

	private WeatherDto getWeather(float lat, float lon) {
		return weatherMapper.map(weatherRepository.getWeatherByLocation(lat, lon));
	}
}
