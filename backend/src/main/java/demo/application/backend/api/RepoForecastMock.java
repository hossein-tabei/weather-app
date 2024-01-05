package demo.application.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

import demo.application.backend.excp.InternalException;
import demo.application.backend.util.DateTimeUtil;

/**
 * The instance of this class is being used when 'apiKey' property is not present in the application config.
 */
@Repository
@ConditionalOnProperty(value="ioConfig.apiKey", matchIfMissing = true)
public class RepoForecastMock implements RepoForecastInterface {
	private Logger logger = LoggerFactory.getLogger(RepoForecastMock.class);
	
	@Override
	public JSONObject currentStatus(double lat, double lon) throws InternalException {
		logger.trace("lat:{}, lon:{}",lat,lon);
		
		JSONObject joMockedStatus = new JSONObject();
		try {
			joMockedStatus.put("name", "Oakland"); 	// String city, 
			joMockedStatus.put("sys", new JSONObject().put("country", "United States"));// String country,
			joMockedStatus.put("weather", new JSONArray().put(new JSONObject().put("id", 800))); 	// String status,
			joMockedStatus.put("main", new JSONObject()
										.put("temp", 19)		// double temperature,
										.put("temp_min", 15)	// double minTemp,
										.put("temp_max", 25)	// double maxTemp,
										.put("humidity", 35)	// int humidity,
										.put("feels_like", 14)	// int realFeel,
								);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		logger.info("Getting: Mock Data, result:{}",joMockedStatus);
		return joMockedStatus;
	}

	@Override
	public JSONObject next5DaysForecast(double lat, double lon) throws InternalException {
		JSONObject joDayStatuses = new JSONObject();
		JSONArray jaDayStatus = new JSONArray();
		try {
			jaDayStatus.put(createDayJson(DateTimeUtil.getEpochTimeFromToday(0), 800, 10, 25, 7.4));
			jaDayStatus.put(createDayJson(DateTimeUtil.getEpochTimeFromToday(1), 800, 8, 20, 6.1));
			jaDayStatus.put(createDayJson(DateTimeUtil.getEpochTimeFromToday(2), 800, 9, 19, 7.5));
			jaDayStatus.put(createDayJson(DateTimeUtil.getEpochTimeFromToday(3), 804, 6, 16, 9.3));
			jaDayStatus.put(createDayJson(DateTimeUtil.getEpochTimeFromToday(4), 804, 7, 18, 7.2));
			
			joDayStatuses.put("list", jaDayStatus);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		logger.info("Getting: Mock Data, result:{}",joDayStatuses);
		return joDayStatuses;
	}
	
	private JSONObject createDayJson(long dateOfDayEpoch, int statusId, double minTemp, double maxTemp, double windSpeed) {
		try {
			return new JSONObject()
					.put("dt", dateOfDayEpoch)
					.put("weather", new JSONArray().put(new JSONObject().put("id", statusId)))
					.put("temp", new JSONObject().put("min", minTemp)
												.put("max", maxTemp))
					.put("speed", windSpeed);
		}
		catch (JSONException e) {return null;}
	}
	
}
