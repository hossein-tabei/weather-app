package demo.application.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

/**
 * The instance of this class is being used when 'apiKey' property is not present in the application config.
 */
@Repository
@ConditionalOnProperty(value="ioConfig.apiKey", matchIfMissing = true)
public class RepoGeoMock implements RepoGeoInterface {
	private Logger logger = LoggerFactory.getLogger(RepoGeoMock.class);
	
	public JSONArray searchGeo(String searchClause) {
		logger.trace("searchClause:{}",searchClause);
		
		JSONArray joMockedLocations = new JSONArray();
		try {
			joMockedLocations.put(new JSONObject().put("name", "Little Rock").put("local_names", new JSONObject()).put("lat", 155).put("lon", 1235).put("state", "Arkansas"		).put("country", "US"));
			joMockedLocations.put(new JSONObject().put("name", "Oakland"	).put("local_names", new JSONObject()).put("lat", 156).put("lon", 1236).put("state", "California"	).put("country", "US"));
			joMockedLocations.put(new JSONObject().put("name", "Miami"		).put("local_names", new JSONObject()).put("lat", 157).put("lon", 1237).put("state", "Florida"		).put("country", "US"));
			joMockedLocations.put(new JSONObject().put("name", "Houston"	).put("local_names", new JSONObject()).put("lat", 158).put("lon", 1238).put("state", "Texas"		).put("country", "US"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		logger.info("Getting: Mock Data, result:{}",joMockedLocations);
		return joMockedLocations;
	}
	
}
