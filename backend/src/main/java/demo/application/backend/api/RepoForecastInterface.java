package demo.application.backend.api;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import demo.application.backend.excp.InternalException;

public interface RepoForecastInterface {
	public JSONObject currentStatus(double lat, double lon) throws InternalException;
	public JSONObject next5DaysForecast(double lat, double lon) throws InternalException;
}
