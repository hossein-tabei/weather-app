package demo.application.backend.geo.data.repository;

import demo.application.backend.geo.data.model.ApiLocation;

import java.util.List;

public interface IGeoRepository {
	List<ApiLocation> searchGeo(String searchClause);
}
