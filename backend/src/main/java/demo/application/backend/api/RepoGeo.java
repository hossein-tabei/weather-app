package demo.application.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import demo.application.backend.config.AppConfig;
import demo.application.backend.excp.InternalException;
import reactor.core.publisher.Mono;

@Repository
@ConditionalOnProperty(value="ioConfig.apiKey")
public class RepoGeo implements RepoGeoInterface {
	private Logger logger = LoggerFactory.getLogger(RepoGeo.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private AppConfig appConfig;
	
	private final String API_KEY;
	private final String HOST;
	private final String SEARCH_PATH;
	private WebClient client;
	
	public RepoGeo(AppConfig appConfig) {
		API_KEY = appConfig.getConfigValue("ioConfig.apiKey");
		HOST = appConfig.getConfigValue("ioConfig.geo.host");
		SEARCH_PATH = appConfig.getConfigValue("ioConfig.geo.path.searchGeo");
		
		client = WebClient.create(HOST);
	}
	
	public JSONArray searchGeo(String searchClause) throws InternalException {
		logger.trace("searchClause:{}",searchClause);
		
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
			logger.trace("Calling: {}, result:{}",uriBuilder.build().toString(),enResult.getBody());
		} catch(WebClientResponseException e) {
			logger.error("Calling searchGeo Failed, API Response Error, statusCode:{}, cause:", e.getStatusCode().value(), e);
			throw new InternalException("Error calling searchGeo service");
		} catch(Exception e) {
			logger.error("Calling searchGeo Failed, cause:", e);
			throw new InternalException("Internal Error");
		}
		
		JSONArray jaResult = null;
		try {
			jaResult = new JSONArray(enResult.getBody());
		} catch (JSONException e) {
			logger.error("Calling service Failed, Invalid json fromat, cause:", e);
			throw new InternalException("Error calling searchGeo service");
		}
		
		logger.info("Calling searchGeo Successful");
		return jaResult;
	}
	
}
