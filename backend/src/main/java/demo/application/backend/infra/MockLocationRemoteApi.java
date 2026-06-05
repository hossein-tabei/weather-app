package demo.application.backend.infra;

import demo.application.backend.infra.model.ApiLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The instance of this class is being used when 'apiKey' property is not present in the application config.
 */
@Repository
@ConditionalOnProperty(value="ioConfig.apiKey", matchIfMissing = true)
@Slf4j
public class MockLocationRemoteApi implements ILocationRemoteApi {

	private final List<ApiLocation> mockedLocations = List.of(
			createApiLocation("Little Rock", new HashMap<>(), 155, 1235, "US", "Arkansas"),
			createApiLocation("Oakland", new HashMap<>(), 156, 1236, "US", "California"),
			createApiLocation("Miami", new HashMap<>(), 157, 1237, "US", "Florida"),
			createApiLocation("Houston", new HashMap<>(), 158, 1238, "US", "Texas")
	);

	public List<ApiLocation> searchLocation(String searchTerm) {
		log.trace("searchTerm:{}", searchTerm);
		log.info("Getting: Mock Data, result:{}", mockedLocations);
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

	public Optional<ApiLocation> findLocation(double lat, double lon) {
		return mockedLocations.stream()
				.filter(location -> location.getLat() == lat && location.getLon() == lon)
				.findFirst();
	}
	
}
