package demo.application.backend.infra;

import demo.application.backend.infra.model.ApiLocation;

import java.util.List;

public interface ILocationRemoteApi {
	List<ApiLocation> searchGeo(String searchClause);
	ApiLocation findLocation(float lat, float lon);
}
