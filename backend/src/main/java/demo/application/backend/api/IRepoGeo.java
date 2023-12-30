package demo.application.backend.api;

import java.util.List;

import demo.application.backend.api.model.ApiLocation;

public interface IRepoGeo {
	public List<ApiLocation> searchGeo(String searchClause);
}
