package demo.application.backend.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import demo.application.backend.api.model.ApiLocation;

/**
 * The instance of this class is being used when 'apiKey' property is not present in the application config.
 */
@Repository
@ConditionalOnProperty(value="ioConfig.apiKey", matchIfMissing = true)
public class RepoGeoMock implements RepoGeoInterface {
	private Logger logger = LoggerFactory.getLogger(RepoGeoMock.class);
	
	public List<ApiLocation> searchGeo(String searchClause) {
		logger.trace("searchClause:{}",searchClause);
		
		List<ApiLocation> mockedApiLocations = new ArrayList<>();
		mockedApiLocations.add(new ApiLocation("Little Rock", new HashMap<String, String>(), 155, 1235, "Arkansas", "US"));
		mockedApiLocations.add(new ApiLocation("Oakland", new HashMap<String, String>(), 156, 1236, "California", "US"));
		mockedApiLocations.add(new ApiLocation("Miami", new HashMap<String, String>(), 157, 1237, "Florida", "US"));
		mockedApiLocations.add(new ApiLocation("Houston", new HashMap<String, String>(), 158, 1238, "Texas", "US"));
		
		logger.info("Getting: Mock Data, result:{}",mockedApiLocations);
		return mockedApiLocations;
	}
	
}
