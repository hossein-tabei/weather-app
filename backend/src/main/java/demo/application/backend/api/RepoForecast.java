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
import org.springframework.web.reactive.function.client.WebClientResponseException;
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
	private final String CURRENT_WEATHER_PATH;
	private final String DAILY_FORECAST_PATH;
	private final String UNITS;
	private final int DAILY_RESULT_COUNT;
	private WebClient client;
	
	public RepoForecast(AppConfig appConfig) {
		API_KEY = appConfig.getConfigValue("ioConfig.apiKey");
		HOST = appConfig.getConfigValue("ioConfig.forecast.host");
		CURRENT_WEATHER_PATH = appConfig.getConfigValue("ioConfig.forecast.path.currentStatus");
		DAILY_FORECAST_PATH = appConfig.getConfigValue("ioConfig.forecast.path.dailyForecast");
		UNITS = appConfig.getConfigValue("weather.units");
		
		String strDailyResultCount = appConfig.getConfigValue("weather.forecast.dailyCount");
		DAILY_RESULT_COUNT = strDailyResultCount != null && strDailyResultCount.matches("[0-9]+")
								? Integer.parseInt(strDailyResultCount)
								: 0;
		
		client = WebClient.create(HOST);
	}
	
	@Override
	public JSONObject currentStatus(double lat, double lon) throws InternalException {
		logger.trace("lat:{}, lon:{}",lat,lon);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(HOST + CURRENT_WEATHER_PATH)
		        .queryParam("lat", lat)
		        .queryParam("lon", lon)
		        .queryParam("units", UNITS)
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
			logger.error("Calling currentStatus Failed, API Response Error, statusCode:{}, cause:", e.getStatusCode().value(), e);
			throw new InternalException("Error calling currentStatus service");
		} catch(Exception e) {
			logger.error("Calling currentStatus Failed, cause:", e);
			throw new InternalException("Internal Error");
		}
		
		JSONObject joResult = null;
		try {
			joResult = new JSONObject(enResult.getBody());
		} catch (JSONException e) {
			logger.error("Calling currentStatus Failed, Invalid json fromat, cause:", e);
			throw new InternalException("Error calling currentStatus service");
		}
		
		logger.info("Calling currentStatus Successful");
		return joResult;
	}
	
	@Override
	public JSONObject next5DaysForecast(double lat, double lon) throws InternalException {
		logger.trace("lat:{}, lon:{}",lat,lon);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(HOST + DAILY_FORECAST_PATH)
		        .queryParam("lat", lat)
		        .queryParam("lon", lon)
		        .queryParam("units", UNITS)
		        .queryParam("cnt", DAILY_RESULT_COUNT)
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
			logger.error("Calling next5DaysForecast Failed, API Response Error, statusCode:{}, cause:", e.getStatusCode().value(), e);
			throw new InternalException("Error calling next5DaysForecast service");
		} catch(Exception e) {
			logger.error("Calling next5DaysForecast Failed, cause:", e);
			throw new InternalException("Internal Error");
		}
		
		JSONObject joResult = null;
		try {
			joResult = new JSONObject(enResult.getBody());
		} catch (JSONException e) {
			logger.error("Calling next5DaysForecast Failed, Invalid json fromat, cause:", e);
			throw new InternalException("Error calling next5DaysForecast service");
		}
		
		logger.info("Calling next5DaysForecast Successful");
		return joResult;
	}
	
}
