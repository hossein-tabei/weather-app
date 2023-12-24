package demo.application.backend.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import demo.application.backend.api.model.ApiLocation;

public class ApiResultConverter {

	@SuppressWarnings("unchecked")
	public static List<ApiLocation> convertObjectsToApiLocations(Object[] rawData) {
		return Arrays.stream(rawData)
				.map(objCurrent -> {
					Map<String, Object> mapCurrent = (Map<String, Object>)objCurrent;
					
					ApiLocation locCurrent = new ApiLocation(
							(String)mapCurrent.get("name"), 
							(Map<String, String>)(mapCurrent.get("local_names")), 
							(double)mapCurrent.get("lat"), 
							(double)mapCurrent.get("lon"), 
							(String)mapCurrent.get("country"), 
							(String)mapCurrent.get("state")
						);
					return locCurrent;
				})
				.collect(Collectors.toList());
	}
}
