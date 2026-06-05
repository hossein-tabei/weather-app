package demo.application.backend.infra;

import demo.application.backend.infra.model.ApiCurrentStatus;

import java.util.List;

public class CurrentStatusTestHelper {

    public static ApiCurrentStatus getApiCurrentStatus(String name, ApiCurrentStatus.Sys sys,
            List<ApiCurrentStatus.WeatherItem> weather, ApiCurrentStatus.Main main) {
        ApiCurrentStatus obj = new ApiCurrentStatus();
        obj.setName(name);
        obj.setSys(sys);
        obj.setWeather(weather);
        obj.setMain(main);
        return obj;
    }

    public static ApiCurrentStatus.Main getApiCurrentStatusMain(Double temp, Double temp_min, Double temp_max,
            Integer humidity, Integer feels_like) {
        ApiCurrentStatus.Main obj = new ApiCurrentStatus.Main();
        obj.setTemp(temp);
        obj.setTemp_min(temp_min);
        obj.setTemp_max(temp_max);
        obj.setHumidity(humidity);
        obj.setFeels_like(feels_like);
        return obj;
    }

}
