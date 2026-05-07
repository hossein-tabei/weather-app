package demo.application.backend.forecast.data.model;

import lombok.Data;

import java.util.List;

@Data
public class ApiDayStatus {

    private List<DayStatusItem> list;

    @Data
    public static class DayStatusItem {
        private Long dt;    // String dayOfWeek, date="yyyy/MM/dd"
        private List<WeatherItem> weather;
        private Temperature temp;
        private Double speed;    // windSpeed
    }

    @Data
    public static class WeatherItem {
        private Integer id;    // int minStatusId, maxStatusId
    }

    @Data
    public static class Temperature {
        private Double min;    // minTemp
        private Double max;    // maxTemp
    }
}