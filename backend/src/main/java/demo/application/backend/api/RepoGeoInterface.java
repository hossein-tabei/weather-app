package demo.application.backend.api;

import java.util.List;

import demo.application.backend.api.model.ApiLocation;
import demo.application.backend.excp.InternalException;

public interface RepoGeoInterface {
	public List<ApiLocation> searchGeo(String searchClause) throws InternalException;
}
