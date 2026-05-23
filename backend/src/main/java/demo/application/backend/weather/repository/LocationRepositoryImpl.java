package demo.application.backend.weather.repository;

import demo.application.backend.infra.ILocationRemoteApi;
import demo.application.backend.weather.LocationMapper;
import demo.application.backend.weather.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {

	private final ILocationRemoteApi iLocationRemoteApi;
	private final LocationMapper locationMapper;

	@Override
	public List<Location> searchLocation(String searchTerm) {
		return locationMapper.map(iLocationRemoteApi.searchGeo(searchTerm));
	}

	@Override
	public Location findLocation(float lat, float lon) {
		return locationMapper.map(iLocationRemoteApi.findLocation(lat, lon));
	}

}
