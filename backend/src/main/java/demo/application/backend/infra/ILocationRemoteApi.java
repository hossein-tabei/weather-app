package demo.application.backend.infra;

import demo.application.backend.infra.model.ApiLocation;

import java.util.List;
import java.util.Optional;

public interface ILocationRemoteApi {
	List<ApiLocation> searchLocation(String searchTerm);
	Optional<ApiLocation> findLocation(double lat, double lon);
}
