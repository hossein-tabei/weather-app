package demo.application.backend.weather.repository;

import demo.application.backend.infra.IForecastRemoteApi;
import demo.application.backend.weather.ForecastMapper;
import demo.application.backend.weather.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherRepositoryImpl implements WeatherRepository {

	private final IForecastRemoteApi iForecastRemoteApi;
	private final ForecastMapper forecastMapper;
	private final LocationRepository locationRepository;

	@Override
	public Weather getWeatherByLocation(float lat, float lon) {

		Location location = locationRepository.findLocation(lat, lon);
		CurrentWeather currentWeather = forecastMapper.map(iForecastRemoteApi.currentStatus(lat,lon));
		List<DayForecast> _5DaysForecast = forecastMapper.map(iForecastRemoteApi.next5DaysForecast(lat,lon).getList());
		List<HourForecast> _24HoursForecast = next24HoursForecast(lat, lon);
		AirQualityIndex airQualityIndex = airQualityIndex(lat, lon);

		Weather weather = new Weather();
		weather.setLocation(location);
		weather.setCurrentWeather(currentWeather);
		weather.set_5DaysForecast(_5DaysForecast);
		weather.set_24HoursForecast(_24HoursForecast);
		weather.setAirQualityIndex(airQualityIndex);
		return weather;
	}

	private List<HourForecast> next24HoursForecast(float lat, float lon) {
		List<HourForecast> next5DayStats = new ArrayList<>();
		next5DayStats.add(new HourForecast(14, 804, 7.4f, "Now"));
		next5DayStats.add(new HourForecast(14, 804, 7.4f, "23:00"));
		next5DayStats.add(new HourForecast(14, 804, 7.4f, "01:00"));
		next5DayStats.add(new HourForecast(14, 804, 7.4f, "02:00"));
		next5DayStats.add(new HourForecast(14, 804, 7.4f, "03:00"));
		next5DayStats.add(new HourForecast(14, 804, 7.4f, "04:00"));
		next5DayStats.add(new HourForecast(14, 804, 7.4f, "05:00"));
		next5DayStats.add(new HourForecast(14, 804, 7.4f, "06:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "07:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "08:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "09:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "10:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "11:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "12:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "13:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "14:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "15:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "16:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "17:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "18:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "19:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "20:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "21:00"));
		next5DayStats.add(new HourForecast(14, 800, 7.4f, "22:00"));
		return next5DayStats;
	}

	private AirQualityIndex airQualityIndex(float lat, float lon) {
		
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
