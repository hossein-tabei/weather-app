package demo.application.backend.infra;

import demo.application.backend.infra.model.ApiCurrentStatus;
import demo.application.backend.infra.model.ApiDayStatus;

public interface IForecastRemoteApi {
	ApiCurrentStatus currentStatus(double lat, double lon);
	ApiDayStatus next5DaysForecast(double lat, double lon);
}
