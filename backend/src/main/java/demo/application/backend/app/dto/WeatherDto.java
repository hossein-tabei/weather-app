package demo.application.backend.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherDto {

    private CurrentWeatherDto currentWeather;
    private List<DayForecastDto> _5DaysForecast;
    private List<HourForecastDto> _24HoursForecast;
    private AirQualityIndexDto airQualityIndex;
}
