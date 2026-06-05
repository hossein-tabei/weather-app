package demo.application.backend.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import demo.application.backend.config.AppConfig;
import demo.application.backend.infra.model.ApiLocation;
import demo.application.backend.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(value="remoteConfig.apiKey")
@Slf4j
public class LocationRemoteApi implements ILocationRemoteApi {
	
	private final String API_KEY;
	private final String HOST;
	private final String SEARCH_PATH;
	private final WebClient client;
	
	public LocationRemoteApi(AppConfig appConfig, WebClient client) {
		API_KEY = appConfig.getApiKey();
		HOST = appConfig.getHost();
		SEARCH_PATH = appConfig.getLocationSearchPath();
		this.client = client;
	}
	
	public List<ApiLocation> searchLocation(String searchTerm) {
		log.trace("searchTerm:{}",searchTerm);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(HOST+SEARCH_PATH)
		        .queryParam("q", searchTerm)
		        .queryParam("limit", 5)
		        .queryParam("appid", API_KEY);

		Mono<ResponseEntity<String>> monoResult = client.get()
				.uri(uriBuilder.build().toString())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(String.class);
			
		ResponseEntity<String> enResult = null;
		try {
			enResult = monoResult.block();
			log.trace("Calling: {}, result:{}",uriBuilder.build(), enResult.getBody());
		} catch(WebClientResponseException e) {
			log.error("Calling searchLocation Failed, API Response Error, statusCode:{}, cause:", e.getStatusCode().value(), e);
			throw e;
		} catch(Exception e) {
			log.error("Calling searchLocation Failed, cause:", e);
			throw new UnhandledException("Unhandled Exception Occurred", e);
		}

		try {
			log.info("Calling searchLocation Successful");
			return JsonUtil.convertToPOJOList(enResult.getBody(), new TypeReference<>(){});
		} catch (JsonProcessingException e) {
			log.error("Calling service Failed, Invalid json format, cause:", e);
			throw new AppJsonProcessingException("Calling searchLocation api Failed. cause: Invalid json format", e);
		}
    }

	/**
	 * The implementation of this method is temporary.
	 * Because location API for individual record is not available yet, we return null.
	 * Later, it should be implemented with real API.
	 */
	@Override
	public Optional<ApiLocation> findLocation(double lat, double lon) {
		return Optional.empty();
	}

}
