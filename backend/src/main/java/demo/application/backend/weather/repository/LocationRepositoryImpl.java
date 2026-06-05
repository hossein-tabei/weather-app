package demo.application.backend.weather.repository;

import demo.application.backend.infra.ILocationRemoteApi;
import demo.application.backend.infra.model.ApiLocation;
import demo.application.backend.weather.LocationMapper;
import demo.application.backend.weather.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {

	private final ILocationRemoteApi iLocationRemoteApi;
	private final LocationMapper locationMapper;

	@Override
	public List<Location> searchLocation(String searchTerm) {
		return locationMapper.map(iLocationRemoteApi.searchLocation(searchTerm));
	}

	@Override
	public Location findLocation(double lat, double lon) {
		Optional<ApiLocation> optApiLocation = iLocationRemoteApi.findLocation(lat, lon);
		if (optApiLocation.isEmpty()) {
			throw new NotFoundException(String.format("No location found for lat: %f, lon: %f", lat, lon));
		}
		return locationMapper.map(optApiLocation.get());
	}

}
