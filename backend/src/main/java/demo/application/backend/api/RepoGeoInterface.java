package demo.application.backend.api;

import org.springframework.boot.configurationprocessor.json.JSONArray;

import demo.application.backend.excp.InternalException;

public interface RepoGeoInterface {
	public JSONArray searchGeo(String searchClause) throws InternalException;
}
