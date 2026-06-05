package demo.application.backend.app;

import demo.application.backend.weather.model.*;

import java.util.List;

public class WeatherTestHelper {

    public static CurrentWeather getCurrentWeather(String city, String country, Integer statusId, Double temperature,
            Double minTemp, Double maxTemp, Integer humidity, Integer realFeel, Integer chanceOfRain) {
        CurrentWeather obj = new CurrentWeather();
        obj.setCity(city);
        obj.setCountry(country);
        obj.setStatusId(statusId);
        obj.setTemperature(temperature);
        obj.setMinTemp(minTemp);
        obj.setMaxTemp(maxTemp);
        obj.setHumidity(humidity);
        obj.setRealFeel(realFeel);
        obj.setChanceOfRain(chanceOfRain);
        return obj;
    }

    public static DayForecast getDayForecast(String dayOfWeek, String date, int minStatusId, int maxStatusId,
            double minTemp, double maxTemp, double windSpeed) {
        DayForecast obj = new DayForecast();
        obj.setDayOfWeek(dayOfWeek);
        obj.setDate(date);
        obj.setMinStatusId(minStatusId);
        obj.setMaxStatusId(maxStatusId);
        obj.setMinTemp(minTemp);
        obj.setMaxTemp(maxTemp);
        obj.setWindSpeed(windSpeed);
        return obj;
    }

    public static HourForecast getHourForecast(int temperature, int statusId, float windSpeed, String hourOfDay) {
        HourForecast obj = new HourForecast();
        obj.setTemperature(temperature);
        obj.setStatusId(statusId);
        obj.setWindSpeed(windSpeed);
        obj.setHourOfDay(hourOfDay);
        return obj;
    }

    //-----------------------------------------------
    // AirQualityIndex
    public static AirQualityIndex getAirQualityIndex(int numericlevel, String verbalLevel, String description,
            String publicationInfo, List<PolutionIndex> polutionIndexes) {
        AirQualityIndex obj = new AirQualityIndex();
        obj.setNumericlevel(numericlevel);
        obj.setVerbalLevel(verbalLevel);
        obj.setDescription(description);
        obj.setPublicationInfo(publicationInfo);
        obj.setPolutionIndexes(polutionIndexes);
        return obj;
    }

}
