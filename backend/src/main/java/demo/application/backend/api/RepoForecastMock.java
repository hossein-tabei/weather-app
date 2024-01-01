package demo.application.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

import demo.application.backend.excp.InternalException;

/**
 * The instance of this class is being used when 'apiKey' property is not present in the application config.
 */
@Repository
@ConditionalOnProperty(value="ioConfig.apiKey", matchIfMissing = true)
public class RepoForecastMock implements RepoForecastInterface {
	private Logger logger = LoggerFactory.getLogger(RepoForecastMock.class);
	
	public JSONObject currentStatus(double lat, double lon) throws InternalException {
		logger.trace("lat:{}, lon:{}",lat,lon);
		
		JSONObject joMockedStatus = new JSONObject();
		try {
			joMockedStatus.put("name", "Oakland"); 	// String city, 
			joMockedStatus.put("sys", new JSONObject().put("country", "United States"));// String country,
			joMockedStatus.put("weather", new JSONArray().put(new JSONObject().put("id", 800))); 	// String status, 
			joMockedStatus.put("main", new JSONObject().put("temp", 19));		// int temperature, 
			joMockedStatus.put("main", new JSONObject().put("temp_min", 15)); 	// int minTemp, 
			joMockedStatus.put("main", new JSONObject().put("temp_max", 25));	// int maxTemp, 
			joMockedStatus.put("main", new JSONObject().put("humidity", 35));	// int humidity, 
			joMockedStatus.put("main", new JSONObject().put("feels_like", 14));// int realFeel,
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		logger.info("Getting: Mock Data, result:{}",joMockedStatus);
		return joMockedStatus;
	}
	
}
