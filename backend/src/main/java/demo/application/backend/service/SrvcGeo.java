package demo.application.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.application.backend.api.RepoGeo;
import demo.application.backend.converter.ApiToDtoConverter;
import demo.application.backend.model.Location;

@Service
public class SrvcGeo {
	private Logger logger = LoggerFactory.getLogger(SrvcGeo.class);
	
	@Autowired
	RepoGeo repoGeo;
	
	public List<Location> searchGeo(String searchClause) {
		logger.trace("searchClause:{}",searchClause);
		return ApiToDtoConverter.convertApiLocationListToLocationList(repoGeo.searchGeo(searchClause));
		
//		List<Location> mockedLocations = new ArrayList<>();
//		mockedLocations.add(new Location(155, 1235, "Little Rock", "Arkansas", "US"));
//		mockedLocations.add(new Location(156, 1236, "Oakland", "California", "US"));
//		mockedLocations.add(new Location(157, 1237, "Miami", "Florida", "US"));
//		mockedLocations.add(new Location(158, 1238, "Houston", "Texas", "US"));
//		return mockedLocations;
	}
}
