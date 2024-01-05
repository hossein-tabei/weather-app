package demo.application.backend.converter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import demo.application.backend.excp.InternalException;
import demo.application.backend.model.CurrentStatus;
import demo.application.backend.model.DayStatus;
import demo.application.backend.model.Location;
import demo.application.backend.util.DateTimeUtil;

public class JsonToModelConverter {
	private static Logger logger = LoggerFactory.getLogger(JsonToModelConverter.class);

	public static List<Location> convertJsonArrayToLocationList(JSONArray jaLocation) throws InternalException {
		
		List<Location> list = new ArrayList<>();
		try {
			for (int i=0; i<jaLocation.length(); i++) {
				JSONObject joLocation = jaLocation.getJSONObject(i);
				list.add(
					new Location(
						joLocation.getDouble("lat"), 
						joLocation.getDouble("lon"), 
						joLocation.getString("name"), 
						joLocation.optString("state"), 
						joLocation.getString("country"))
				);
			}
		} catch (JSONException e) {
			logger.error("Converting json to model Failed, cause:", e);
			throw new InternalException("Error parsing geolocation data");
		}
		
		return list;
	}
	
	public static CurrentStatus convertJsonObjectToCurrentStatus(JSONObject joCurrentStatus) throws InternalException {
		
		CurrentStatus currentStatus = null;
		try {
			currentStatus = new CurrentStatus(
					joCurrentStatus.getString("name"), 	// String city, 
					joCurrentStatus.getJSONObject("sys").getString("country"), 	// String country, 
					joCurrentStatus.getJSONArray("weather").getJSONObject(0).getInt("id"), 	// String status, 
					joCurrentStatus.getJSONObject("main").getDouble("temp"),		// int temperature, 
					joCurrentStatus.getJSONObject("main").getDouble("temp_min"), 	// int minTemp, 
					joCurrentStatus.getJSONObject("main").getDouble("temp_max"),	// int maxTemp, 
					joCurrentStatus.getJSONObject("main").getInt("humidity"),	// int humidity, 
					joCurrentStatus.getJSONObject("main").getInt("feels_like"),	// int realFeel, 
					10);	// int chanceOfRain
		} catch (JSONException e) {
			logger.error("Converting json to model Failed, cause:", e);
			throw new InternalException("Error parsing current forecast data");
		}
		
		return currentStatus;
	}
	
	public static List<DayStatus> convertJsonArrayToDayStatusList(JSONObject joDayStatuses) throws InternalException {
		
		List<DayStatus> list = new ArrayList<>();
		try {
			JSONArray jaDayStatus = joDayStatuses.getJSONArray("list");
			for (int i=0; i<jaDayStatus.length(); i++) {
				JSONObject joDayStatus = jaDayStatus.getJSONObject(i);
				list.add(
					new DayStatus(
						resolveDayOfWeek(joDayStatus.getLong("dt")), 						// String dayOfWeek,
						DateTimeUtil.convertEpochToStringDate(joDayStatus.getLong("dt")), 	// String date "yyyy/MM/dd"
						joDayStatus.getJSONArray("weather").getJSONObject(0).getInt("id"), 	// int minStatusId,
						joDayStatus.getJSONArray("weather").getJSONObject(0).getInt("id"), 	// int maxStatusId,
						joDayStatus.getJSONObject("temp").getDouble("min"), // double minTemp, 
						joDayStatus.getJSONObject("temp").getDouble("max"), // double maxTemp,
						joDayStatus.getDouble("speed") // double windSpeed
					)
				);
			}
		} catch (JSONException e) {
			logger.error("Converting json to model Failed, cause:", e);
			throw new InternalException("Error parsing DayStatus data");
		}
		
		return list;
	}
	
	private static String resolveDayOfWeek(long epochDate) {
		String dayOfWeek;
		if (DateTimeUtil.isToday(epochDate)) {
			dayOfWeek = "Today";
		} else if (DateTimeUtil.isTomorrow(epochDate)) {
			dayOfWeek = "Tomorrow";
		} else {
			dayOfWeek = DateTimeUtil.getDayOfWeekFromEpochDate(epochDate);
		}
		return dayOfWeek;
	}
}
