package demo.application.backend.forecast.api.controller;

import demo.application.backend.excp.InternalException;
import demo.application.backend.model.*;
import demo.application.backend.forecast.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/forecast")
@RequiredArgsConstructor
public class ForecastController {

	private final ForecastService forecastService;
	
	@GetMapping(path = "/now", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<CurrentStatus> currentStatus(@RequestParam float lat, @RequestParam  float lon)
			throws InternalException {
		CurrentStatus fetchResult = forecastService.currentStatus(lat, lon);
		return new DTOResultWrapper<>("Operation Done Successfully", fetchResult);
	}
	
	@GetMapping(path = "/next5days", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<DayStatus>> next5DaysForecast(@RequestParam float lat, @RequestParam  float lon)
			throws InternalException {
		List<DayStatus> fetchResult = forecastService.next5DaysForecast(lat, lon);
		return new DTOResultWrapper<>("Operation Done Successfully", fetchResult);
	}
	
	@GetMapping(path = "/next24hours", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<HourStatus>> next24HoursForecast(@RequestParam float lat, @RequestParam  float lon) {
		List<HourStatus> fetchResult = forecastService.next24HoursForecast(lat, lon);
		return new DTOResultWrapper<List<HourStatus>>("Operation Done Successfully", fetchResult);
	}
	
	@GetMapping(path = "/aqi", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<AirQualityIndex> airQualityIndex(@RequestParam float lat, @RequestParam  float lon) {
		AirQualityIndex fetchResult = forecastService.airQualityIndex(lat, lon);
		return new DTOResultWrapper<AirQualityIndex>("Operation Done Successfully", fetchResult);
	}
}
