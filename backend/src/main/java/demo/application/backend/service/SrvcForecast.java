package demo.application.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.application.backend.api.RepoForecastInterface;
import demo.application.backend.converter.JsonToModelConverter;
import demo.application.backend.excp.InternalException;
import demo.application.backend.model.AirQualityIndex;
import demo.application.backend.model.CurrentStatus;
import demo.application.backend.model.DayStatus;
import demo.application.backend.model.HourStatus;
import demo.application.backend.model.PolutionIndex;

@Service
public class SrvcForecast {
	private Logger logger = LoggerFactory.getLogger(SrvcGeo.class);
	
	@Autowired
	RepoForecastInterface repoForecast;
	
	public SrvcForecast(RepoForecastInterface repoForecast) {
		logger.trace("repoForecast type:{}",repoForecast.getClass().toString());
	}
	
	public CurrentStatus currentStatus(float lat, float lon) throws InternalException {
		logger.trace("lat:{}, lon:{}",lat,lon);
		return JsonToModelConverter.convertJsonObjectToCurrentStatus(repoForecast.currentStatus(lat,lon));
	}
	
	public List<DayStatus> next5DaysForecast(float lat, float lon) throws InternalException {
		logger.trace("lat:{}, lon:{}",lat,lon);
		return JsonToModelConverter.convertJsonArrayToDayStatusList(repoForecast.next5DaysForecast(lat,lon));
		
		
//		List<DayStatus> next5DayStats = new ArrayList<>();
//		next5DayStats.add(new DayStatus("Today"		, "2023/11/27", 804, 800, 6, 16, 7.4f));
//		next5DayStats.add(new DayStatus("Tomorrow"	, "2023/11/28", 804, 800, 6, 16, 7.4f));
//		next5DayStats.add(new DayStatus("Wednesday"	, "2023/11/29", 804, 800, 6, 16, 7.4f));
//		next5DayStats.add(new DayStatus("Thursday"	, "2023/11/30", 804, 800, 6, 16, 7.4f));
//		next5DayStats.add(new DayStatus("Friday"	, "2023/12/01", 804, 800, 6, 16, 7.4f));
//		return next5DayStats;
	}
	
	public List<HourStatus> next24HoursForecast(float lat, float lon) {
		List<HourStatus> next5DayStats = new ArrayList<>();
		next5DayStats.add(new HourStatus(14, 804, 7.4f, "Now"));
		next5DayStats.add(new HourStatus(14, 804, 7.4f, "23:00"));
		next5DayStats.add(new HourStatus(14, 804, 7.4f, "01:00"));
		next5DayStats.add(new HourStatus(14, 804, 7.4f, "02:00"));
		next5DayStats.add(new HourStatus(14, 804, 7.4f, "03:00"));
		next5DayStats.add(new HourStatus(14, 804, 7.4f, "04:00"));
		next5DayStats.add(new HourStatus(14, 804, 7.4f, "05:00"));
		next5DayStats.add(new HourStatus(14, 804, 7.4f, "06:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "07:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "08:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "09:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "10:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "11:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "12:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "13:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "14:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "15:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "16:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "17:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "18:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "19:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "20:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "21:00"));
		next5DayStats.add(new HourStatus(14, 800, 7.4f, "22:00"));
		return next5DayStats;
	}
	
	public AirQualityIndex airQualityIndex(float lat, float lon) {
		
		List<PolutionIndex> polutionIndexes = new ArrayList<>();
		polutionIndexes.add(new PolutionIndex("PM2.5", 28.9f));
		polutionIndexes.add(new PolutionIndex("PM10", 16.4f));
		polutionIndexes.add(new PolutionIndex("SO2", 3));
		polutionIndexes.add(new PolutionIndex("NO2", 19));
		polutionIndexes.add(new PolutionIndex("O3", 12.7f));
		polutionIndexes.add(new PolutionIndex("CO", 2.7f));
		return new AirQualityIndex(28, "Good",
											"Air quality is good. A perfect day for a walk!",
											"Oakland Published at 07:30", polutionIndexes);
	}
}
