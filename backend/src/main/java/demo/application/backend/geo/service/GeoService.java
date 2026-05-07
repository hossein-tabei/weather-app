package demo.application.backend.geo.service;

import demo.application.backend.model.Location;

import java.util.List;

public interface GeoService {

	List<Location> searchGeo(String searchClause);
}
