package demo.application.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import demo.application.backend.model.Location;

@Repository
public class GeoRepository {
	public List<Location> searchGeo(String searchClause) {
		System.out.println("searchClause:"+searchClause);
		List<Location> mockedLocations = new ArrayList<>();
		mockedLocations.add(new Location(155, 1235, "Little Rock", "Arkansas", "US"));
		mockedLocations.add(new Location(156, 1236, "Oakland", "California", "US"));
		mockedLocations.add(new Location(157, 1237, "Miami", "Florida", "US"));
		mockedLocations.add(new Location(158, 1238, "Houston", "Texas", "US"));
		return mockedLocations;
	}
}
