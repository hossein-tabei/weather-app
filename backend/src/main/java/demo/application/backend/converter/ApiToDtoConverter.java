package demo.application.backend.converter;

import java.util.List;
import java.util.stream.Collectors;

import demo.application.backend.api.model.ApiLocation;
import demo.application.backend.model.Location;

public class ApiToDtoConverter {

	public static List<Location> convertApiLocationListToLocationList(List<ApiLocation> apiLocations) {
		
		return apiLocations.stream().map(apiLocation -> 
					new Location(
						apiLocation.getLat(), 
						apiLocation.getLon(), 
						apiLocation.getName(), 
						apiLocation.getState(), 
						apiLocation.getCountry())
					)
					.collect(Collectors.toList());
	}
}
