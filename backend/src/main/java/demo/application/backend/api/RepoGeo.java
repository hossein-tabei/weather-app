package demo.application.backend.api;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import demo.application.backend.api.model.ApiLocation;
import demo.application.backend.config.AppConfig;
import demo.application.backend.converter.ApiResultConverter;
import reactor.core.publisher.Mono;

@Repository
@ConditionalOnProperty(value="ioConfig.apiKey")
public class RepoGeo implements IRepoGeo {
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
	
	
	public List<ApiLocation> searchGeo(String searchClause) {
		logger.trace("searchClause:{}",searchClause);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(HOST+SEARCH_PATH)
		        .queryParam("q", searchClause)
		        .queryParam("limit", 5)
		        .queryParam("appid", API_KEY);
		
		Mono<Object[]> monoResult = client.get()
				.uri(uriBuilder.build().toString())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
//				.toEntity(JSONPObject.class)
				.bodyToMono(Object[].class);
		
		Object[] objResult = monoResult.block();
		
		logger.info("Calling: {}, result:{}",uriBuilder.build().toString(),Arrays.asList(objResult).toString());
		return ApiResultConverter.convertObjectsToApiLocations(objResult);
	}
	
}
