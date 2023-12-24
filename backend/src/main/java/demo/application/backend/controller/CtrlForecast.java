package demo.application.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.application.backend.model.AirQualityIndex;
import demo.application.backend.model.CurrentStatus;
import demo.application.backend.model.DTOResultWrapper;
import demo.application.backend.model.DayStatus;
import demo.application.backend.model.HourStatus;
import demo.application.backend.model.Location;
import demo.application.backend.service.SrvcForecast;

@RestController
@RequestMapping("/api/forecast")
public class CtrlForecast {

	@Autowired private SrvcForecast srvcForecast;
	
	@GetMapping(path = "/now", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<CurrentStatus> currentStatus(@RequestParam float lat, @RequestParam  float lon) {
		CurrentStatus fetchResult = srvcForecast.currentStatus(lat, lon);
		return new DTOResultWrapper<CurrentStatus>(1, "Operation Done Successfully", fetchResult);
	}
	
	@GetMapping(path = "/next5days", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<DayStatus>> next5DaysForecast(@RequestParam float lat, @RequestParam  float lon) {
		List<DayStatus> fetchResult = srvcForecast.next5DaysForecast(lat, lon);
		return new DTOResultWrapper<List<DayStatus>>(1, "Operation Done Successfully", fetchResult);
	}
	
	@GetMapping(path = "/next24hours", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<HourStatus>> next24HoursForecast(@RequestParam float lat, @RequestParam  float lon) {
		List<HourStatus> fetchResult = srvcForecast.next24HoursForecast(lat, lon);
		return new DTOResultWrapper<List<HourStatus>>(1, "Operation Done Successfully", fetchResult);
	}
	
	@GetMapping(path = "/aqi", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<AirQualityIndex> airQualityIndex(@RequestParam float lat, @RequestParam  float lon) {
		AirQualityIndex fetchResult = srvcForecast.airQualityIndex(lat, lon);
		return new DTOResultWrapper<AirQualityIndex>(1, "Operation Done Successfully", fetchResult);
	}
}
