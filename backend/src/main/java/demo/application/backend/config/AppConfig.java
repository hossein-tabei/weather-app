package demo.application.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppConfig {

	private final Environment env;

	public String getApiKey() {
		return env.getProperty("remoteConfig.apiKey");
	}

	public String getHost() {
		return env.getProperty("remoteConfig.host");
	}

	// location specific configs
	public String getLocationSearchPath() {
		return env.getProperty("remoteConfig.geo.path.searchGeo");
	}

	// weather specific configs
	public String getCurrentWeatherPath() {
		return env.getProperty("remoteConfig.forecast.path.currentStatus");
	}

	public String getDailyForecastPath() {
		return env.getProperty("remoteConfig.forecast.path.dailyForecast");
	}

	// api behaviour config
	public String getUnits() {
		return env.getProperty("weather.units");
	}

	public String getDailyResultCount() {
		return env.getProperty("weather.forecast.dailyCount");
	}
}
