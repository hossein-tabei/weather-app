package demo.application.backend.app;

import demo.application.backend.app.dto.WeatherDto;
import demo.application.backend.weather.model.Weather;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherDto map(Weather weather);
}
