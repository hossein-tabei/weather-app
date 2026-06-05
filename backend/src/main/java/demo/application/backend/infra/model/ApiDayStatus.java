package demo.application.backend.infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WeatherItem {
        private Integer id;    // int minStatusId, maxStatusId
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Temperature {
        private Double min;    // minTemp
        private Double max;    // maxTemp
    }
}