package demo.application.backend.app;

import demo.application.backend.app.dto.*;
import demo.application.backend.weather.LocationMapper;
import demo.application.backend.weather.model.Location;
import demo.application.backend.weather.repository.LocationRepository;
import demo.application.backend.weather.repository.WeatherRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

	private final WeatherMapper weatherMapper;
	private final WeatherRepository weatherRepository;

	private final LocationMapper locationMapper;
	private final LocationRepository locationRepository;

	@GetMapping(path="/location/search", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public List<LocationDto> searchLocation(@RequestParam("searchTerm") @NotBlank String searchTerm) {
		List<Location> locations = locationRepository.searchLocation(searchTerm);
		return locationMapper.mapTo(locations);
	}

	@GetMapping(path = "/now", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public CurrentWeatherDto currentWeather(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
		WeatherDto weatherDto = getWeather(lat,  lon);
		return weatherDto.getCurrentWeather();
	}

	@GetMapping(path = "/forecast/next5days", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public List<DayForecastDto> next5DaysForecast(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
		WeatherDto weatherDto = getWeather(lat,  lon);
		return weatherDto.get_5DaysForecast();
	}

	@GetMapping(path = "/forecast/next24hours", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public List<HourForecastDto> next24HoursForecast(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
		WeatherDto weatherDto = getWeather(lat,  lon);
		return weatherDto.get_24HoursForecast();
	}

	@GetMapping(path = "/aqi", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public AirQualityIndexDto airQualityIndex(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
		WeatherDto weatherDto = getWeather(lat,  lon);
		return weatherDto.getAirQualityIndex();
	}

	private WeatherDto getWeather(double lat, double lon) {
		return weatherMapper.map(weatherRepository.getWeatherByLocation(lat, lon));
	}
}
