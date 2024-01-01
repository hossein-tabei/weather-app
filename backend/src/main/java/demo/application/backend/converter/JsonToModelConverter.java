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
import demo.application.backend.model.Location;

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
					joCurrentStatus.getJSONObject("main").getInt("temp"),		// int temperature, 
					joCurrentStatus.getJSONObject("main").getInt("temp_min"), 	// int minTemp, 
					joCurrentStatus.getJSONObject("main").getInt("temp_max"),	// int maxTemp, 
					joCurrentStatus.getJSONObject("main").getInt("humidity"),	// int humidity, 
					joCurrentStatus.getJSONObject("main").getInt("feels_like"),	// int realFeel, 
					10);	// int chanceOfRain
		} catch (JSONException e) {
			logger.error("Converting json to model Failed, cause:", e);
			throw new InternalException("Error parsing current forecast data");
		}
		
		return currentStatus;
	}
}
