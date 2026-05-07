package demo.application.backend.geo.data.repository;

import demo.application.backend.geo.data.model.ApiLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The instance of this class is being used when 'apiKey' property is not present in the application config.
 */
@Repository
@ConditionalOnProperty(value="ioConfig.apiKey", matchIfMissing = true)
@Slf4j
public class MockGeoRepository implements IGeoRepository {
	
	public List<ApiLocation> searchGeo(String searchClause) {
		log.trace("searchClause:{}",searchClause);

		List<ApiLocation> mockedLocations = new ArrayList<>();
		mockedLocations.add(createApiLocation("Little Rock", new HashMap<>(), 155, 1235, "US", "Arkansas"));
		mockedLocations.add(createApiLocation("Oakland", new HashMap<>(), 156, 1236, "US", "California"));
		mockedLocations.add(createApiLocation("Miami", new HashMap<>(), 157, 1237, "US", "Florida"));
		mockedLocations.add(createApiLocation("Houston", new HashMap<>(), 158, 1238, "US", "Texas"));
		
		log.info("Getting: Mock Data, result:{}",mockedLocations);
		return mockedLocations;
	}

	private ApiLocation createApiLocation(String name, Map<String, String> local_names, double lat, double lon, String country, String state) {
		ApiLocation apiLocation = new ApiLocation();
		apiLocation.setName(name);
		apiLocation.setLocal_names(local_names);
		apiLocation.setLat(lat);
		apiLocation.setLon(lon);
		apiLocation.setCountry(country);
		apiLocation.setState(state);

		return apiLocation;
	}
	
}
