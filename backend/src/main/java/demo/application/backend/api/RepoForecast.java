package demo.application.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import demo.application.backend.config.AppConfig;
import demo.application.backend.excp.InternalException;
import reactor.core.publisher.Mono;

@Repository
@ConditionalOnProperty(value="ioConfig.apiKey")
public class RepoForecast implements RepoForecastInterface {
	private Logger logger = LoggerFactory.getLogger(RepoForecast.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private AppConfig appConfig;
	
	private final String API_KEY;
	private final String HOST;
	private final String SEARCH_PATH;
	private WebClient client;
	
	public RepoForecast(AppConfig appConfig) {
		API_KEY = appConfig.getConfigValue("ioConfig.apiKey");
		HOST = appConfig.getConfigValue("ioConfig.forecast.host");
		SEARCH_PATH = appConfig.getConfigValue("ioConfig.forecast.path.currentStatus");
		
		client = WebClient.create(HOST);
	}
	
	public JSONObject currentStatus(double lat, double lon) throws InternalException {
		logger.trace("lat:{}, lon:{}",lat,lon);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(HOST+SEARCH_PATH)
		        .queryParam("lat", lat)
		        .queryParam("lon", lon)
		        .queryParam("appid", API_KEY);
		
		Mono<ResponseEntity<String>> monoResult = client.get()
				.uri(uriBuilder.build().toString())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(String.class);
			
		ResponseEntity<String> enResult = null;
		try {
			enResult = monoResult.block();
		} catch(Exception e) {
			logger.error("Calling service Failed, cause:", e);
			throw new InternalException("Internal Error");
		} finally {
			logger.trace("Calling: {}, result:{}",uriBuilder.build().toString(),enResult.getBody());
		}
		
		logger.trace("statusCode:{}",enResult.getStatusCode().value());
		if (!enResult.getStatusCode().is2xxSuccessful()) {
			logger.error("Calling service Failed, API Response Error, status:{}", enResult.getStatusCode().value());
			throw new InternalException("Error calling current forecast service");
		}
		
		JSONObject joResult = null;
		try {
			joResult = new JSONObject(enResult.getBody());
		} catch (JSONException e) {
			logger.error("Calling service Failed, Invalid json fromat, cause:", e);
			throw new InternalException("Error calling current forecast service");
		}
		
		logger.info("Calling service Successful");
		return joResult;
	}
	
}
