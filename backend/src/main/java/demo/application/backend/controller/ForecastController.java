package demo.application.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.application.backend.model.CurrentStatus;
import demo.application.backend.model.DayStatus;
import demo.application.backend.model.HourStatus;
import demo.application.backend.repository.ForecastRepository;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

	@Autowired private ForecastRepository forecastRepository;
	
	@GetMapping(path = "/now", produces = {MediaType.APPLICATION_JSON_VALUE})
	public CurrentStatus currentStatus(@RequestParam float lat, @RequestParam  float lon) {
		return forecastRepository.currentStatus(lat, lon);
	}
	
	@GetMapping(path = "/next5days", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<DayStatus> next5DaysForecast(@RequestParam float lat, @RequestParam  float lon) {
		return forecastRepository.next5DaysForecast(lat, lon);
	}
	
	@GetMapping(path = "/next24hours", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<HourStatus> next24HoursForecast(@RequestParam float lat, @RequestParam  float lon) {
		return forecastRepository.next24HoursForecast(lat, lon);
	}
}
