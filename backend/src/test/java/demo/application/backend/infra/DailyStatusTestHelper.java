package demo.application.backend.infra;

import demo.application.backend.infra.model.ApiDayStatus;

import java.util.ArrayList;
import java.util.List;

public class DailyStatusTestHelper {

    public static ApiDayStatus getApiDayStatus(List<ApiDayStatus.DayStatusItem> list) {
        ApiDayStatus obj = new ApiDayStatus();
        obj.setList(list);
        return obj;
    }

    public static ApiDayStatus.DayStatusItem getDayStatusItem(Long dt, Integer weatherId, Double minTemp, Double maxTemp, Double speed) {
        ApiDayStatus.DayStatusItem obj = new ApiDayStatus.DayStatusItem();
        obj.setDt(dt);
        obj.setWeather(new ArrayList<>(List.of(new ApiDayStatus.WeatherItem(weatherId))));
        obj.setTemp(new ApiDayStatus.Temperature(minTemp, maxTemp));
        obj.setSpeed(speed);
        return obj;
    }

}
