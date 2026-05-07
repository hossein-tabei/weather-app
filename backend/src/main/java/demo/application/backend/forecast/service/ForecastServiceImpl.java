package demo.application.backend.forecast.service;

import demo.application.backend.forecast.ForecastMapper;
import demo.application.backend.forecast.data.repository.IForecastRepository;
import demo.application.backend.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

	private final IForecastRepository iForecastRepository;
	private final ForecastMapper forecastMapper;

	@Override
	public CurrentStatus currentStatus(float lat, float lon) {
		return forecastMapper.map(iForecastRepository.currentStatus(lat,lon));
	}

	@Override
	public List<DayStatus> next5DaysForecast(float lat, float lon) {
		return forecastMapper.map(iForecastRepository.next5DaysForecast(lat,lon).getList());
	}

	@Override
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

	@Override
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
