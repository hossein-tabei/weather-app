package demo.application.backend.weather.model;

import lombok.Data;

import java.util.List;

@Data
public class Weather {

    private Location location;
    private CurrentWeather currentWeather;
    private List<DayForecast> _5DaysForecast;
    private List<HourForecast> _24HoursForecast;
    private AirQualityIndex airQualityIndex;
}
