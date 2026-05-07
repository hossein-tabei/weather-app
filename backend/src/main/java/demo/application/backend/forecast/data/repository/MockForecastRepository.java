package demo.application.backend.forecast.data.repository;

import demo.application.backend.forecast.data.model.ApiCurrentStatus;
import demo.application.backend.forecast.data.model.ApiDayStatus;
import demo.application.backend.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * The instance of this class is being used when 'apiKey' property is not present in the application config.
 */
@ConditionalOnProperty(value="ioConfig.apiKey", matchIfMissing = true)
@Repository
@Slf4j
public class MockForecastRepository implements IForecastRepository {

	@Override
	public ApiCurrentStatus currentStatus(double lat, double lon) {
		log.trace("lat:{}, lon:{}",lat,lon);

		ApiCurrentStatus joMockedStatus = new ApiCurrentStatus();
		ApiCurrentStatus.Sys sys = new ApiCurrentStatus.Sys();
		sys.setCountry("United States");// String country,

		ApiCurrentStatus.WeatherItem weatherItem = new ApiCurrentStatus.WeatherItem();
		weatherItem.setId(800);// String status,

		ApiCurrentStatus.Main main = new ApiCurrentStatus.Main();
		main.setTemp(19D);// double temperature,
		main.setTemp_min(15D);// double minTemp,
		main.setTemp_max(25D);// double maxTemp,
		main.setHumidity(35);// int humidity,
		main.setFeels_like(14);// int realFeel,

		joMockedStatus.setName("Oakland"); 	// String city,
		joMockedStatus.setSys(sys);
		joMockedStatus.setWeather(new ArrayList<>(List.of(weatherItem)));
		joMockedStatus.setMain(main);
		
		log.info("Getting: Mock Data, result:{}",joMockedStatus);
		return joMockedStatus;
	}

	@Override
	public ApiDayStatus next5DaysForecast(double lat, double lon) {
		List<ApiDayStatus.DayStatusItem> listDayStatus = new ArrayList<>();
		listDayStatus.add(createDayJson(DateTimeUtil.getEpochTimeFromToday(0), 800, 10, 25, 7.4));
		listDayStatus.add(createDayJson(DateTimeUtil.getEpochTimeFromToday(1), 800, 8, 20, 6.1));
		listDayStatus.add(createDayJson(DateTimeUtil.getEpochTimeFromToday(2), 800, 9, 19, 7.5));
		listDayStatus.add(createDayJson(DateTimeUtil.getEpochTimeFromToday(3), 804, 6, 16, 9.3));
		listDayStatus.add(createDayJson(DateTimeUtil.getEpochTimeFromToday(4), 804, 7, 18, 7.2));

		ApiDayStatus apiDayStatus = new ApiDayStatus();
		apiDayStatus.setList(listDayStatus);
		
		log.info("Getting: Mock Data, result:{}",apiDayStatus);
		return apiDayStatus;
	}
	
	private ApiDayStatus.DayStatusItem createDayJson(long dateOfDayEpoch, int statusId, double minTemp, double maxTemp, double windSpeed) {
		ApiDayStatus.WeatherItem weatherItem = new ApiDayStatus.WeatherItem();
		weatherItem.setId(statusId);

		ApiDayStatus.Temperature temperature = new ApiDayStatus.Temperature();
		temperature.setMin(minTemp);
		temperature.setMax(maxTemp);

		ApiDayStatus.DayStatusItem item = new ApiDayStatus.DayStatusItem();
		item.setDt(dateOfDayEpoch);
		item.setWeather(new ArrayList<>(List.of(weatherItem)));
		item.setTemp(temperature);
		item.setSpeed(windSpeed);

		return item;
	}
	
}
