package demo.application.backend.weather.repository;

import demo.application.backend.weather.model.Location;

import java.util.List;

public interface LocationRepository {

	List<Location> searchLocation(String searchClause);
	Location findLocation(double lat, double lon);
}
