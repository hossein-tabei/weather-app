package demo.application.backend.forecast.data.repository;

import demo.application.backend.forecast.data.model.ApiCurrentStatus;
import demo.application.backend.forecast.data.model.ApiDayStatus;

public interface IForecastRepository {
	ApiCurrentStatus currentStatus(double lat, double lon);
	ApiDayStatus next5DaysForecast(double lat, double lon);
}
