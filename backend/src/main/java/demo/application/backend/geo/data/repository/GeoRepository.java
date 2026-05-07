package demo.application.backend.geo.data.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.application.backend.config.AppConfig;
import demo.application.backend.geo.data.model.ApiLocation;
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

@Repository
@ConditionalOnProperty(value="ioConfig.apiKey")
@Slf4j
public class GeoRepository implements IGeoRepository {
	
	private final String API_KEY;
	private final String HOST;
	private final String SEARCH_PATH;
	private WebClient client;
	
	public GeoRepository(AppConfig appConfig) {
		API_KEY = appConfig.getConfigValue("ioConfig.apiKey");
		HOST = appConfig.getConfigValue("ioConfig.geo.host");
		SEARCH_PATH = appConfig.getConfigValue("ioConfig.geo.path.searchGeo");
		
		client = WebClient.create(HOST);
	}
	
	public List<ApiLocation> searchGeo(String searchClause) {
		log.trace("searchClause:{}",searchClause);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(HOST+SEARCH_PATH)
		        .queryParam("q", searchClause)
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
			log.error("Calling searchGeo Failed, API Response Error, statusCode:{}, cause:", e.getStatusCode().value(), e);
			throw new RuntimeException("Error calling searchGeo service", e);
		} catch(Exception e) {
			log.error("Calling searchGeo Failed, cause:", e);
			throw new RuntimeException("Internal Error", e);
		}

		try {
			log.info("Calling searchGeo Successful");
			return JsonUtil.convertToPOJOList(enResult.getBody(), ApiLocation.class);
		} catch (JsonProcessingException e) {
			log.error("Calling service Failed, Invalid json format, cause:", e);
			throw new RuntimeException("Error calling searchGeo service", e);
		}
    }
	
}
