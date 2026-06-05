package demo.application.backend.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.application.backend.config.AppConfig;
import demo.application.backend.infra.model.ApiCurrentStatus;
import demo.application.backend.infra.model.ApiDayStatus;
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

@ConditionalOnProperty(value="remoteConfig.apiKey")
@Repository
@Slf4j
public class ForecastRemoteApi implements IForecastRemoteApi {
	
	private final String API_KEY;
	private final String HOST;
	private final String CURRENT_WEATHER_PATH;
	private final String DAILY_FORECAST_PATH;
	private final String UNITS;
	private final int DAILY_RESULT_COUNT;
	private WebClient client;
	
	public ForecastRemoteApi(AppConfig appConfig, WebClient client) {
		API_KEY = appConfig.getApiKey();
		HOST = appConfig.getHost();
		CURRENT_WEATHER_PATH = appConfig.getCurrentWeatherPath();
		DAILY_FORECAST_PATH = appConfig.getDailyForecastPath();
		UNITS = appConfig.getUnits();
		
		String strDailyResultCount = appConfig.getDailyResultCount();
		DAILY_RESULT_COUNT = strDailyResultCount != null && strDailyResultCount.matches("[0-9]+")
								? Integer.parseInt(strDailyResultCount)
								: 0;
		
		this.client = client;
	}
	
	@Override
	public ApiCurrentStatus currentStatus(double lat, double lon) {
		log.debug("lat:{}, lon:{}",lat,lon);
		
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
			
		ResponseEntity<String> enResult;
		try {
			enResult = monoResult.block();
			log.debug("Calling: {}, result:{}",uriBuilder.build(),enResult.getBody());
		} catch(WebClientResponseException e) {
			log.error("Calling currentStatus Failed, API Response Error, statusCode:{}, cause:", e.getStatusCode().value(), e);
			throw e;
		} catch(Exception e) {
			log.error("Calling searchGeo Failed, cause:", e);
			throw new UnhandledException("Unhandled Exception Occurred", e);
		}
		
		try {
			log.info("Calling currentStatus Successful");
			return JsonUtil.convertToPOJO(enResult.getBody(), ApiCurrentStatus.class);
		} catch (JsonProcessingException e) {
			log.error("Calling currentStatus Failed, Invalid json format, cause:", e);
			throw new AppJsonProcessingException("Calling currentStatus api Failed. cause: Invalid json format", e);
        }

    }
	
	@Override
	public ApiDayStatus next5DaysForecast(double lat, double lon) {
		log.debug("lat:{}, lon:{}",lat,lon);
		
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
			
		ResponseEntity<String> enResult;
		try {
			enResult = monoResult.block();
			log.debug("Calling: {}, result:{}",uriBuilder.build(), enResult.getBody());
		} catch(WebClientResponseException e) {
			log.error("Calling next5DaysForecast Failed, API Response Error, statusCode:{}, cause:", e.getStatusCode().value(), e);
			throw e;
		} catch(Exception e) {
			log.error("Calling next5DaysForecast Failed, cause:", e);
			throw new UnhandledException("Unhandled Exception Occurred", e);
		}
		
		try {
			log.info("Calling next5DaysForecast Successful");
			return JsonUtil.convertToPOJO(enResult.getBody(), ApiDayStatus.class);
		} catch (JsonProcessingException e) {
			log.error("Calling next5DaysForecast Failed, Invalid json format, cause:", e);
			throw new AppJsonProcessingException("Calling next5DaysForecast api Failed. cause: Invalid json format", e);
		}
	}
	
}
