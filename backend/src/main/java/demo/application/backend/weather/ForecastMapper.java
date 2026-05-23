package demo.application.backend.weather;

import demo.application.backend.infra.model.ApiCurrentStatus;
import demo.application.backend.infra.model.ApiDayStatus;
import demo.application.backend.weather.model.CurrentWeather;
import demo.application.backend.weather.model.DayForecast;
import demo.application.backend.util.DateTimeUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ForecastMapper {

    @Mapping(target="city", source="name")
    @Mapping(target="country", source="sys.country")
    @Mapping(target="statusId", source="weather", qualifiedByName = "getStatusId4CurrenStatus")
    @Mapping(target="temperature", source="main.temp")
    @Mapping(target="minTemp", source="main.temp_min")
    @Mapping(target="maxTemp", source="main.temp_max")
    @Mapping(target="humidity", source="main.humidity")
    @Mapping(target="realFeel", source="main.feels_like")
    CurrentWeather map(ApiCurrentStatus apiCurrentStatus);

    @Mapping(target="dayOfWeek", source="dt", qualifiedByName = "resolveDayOfWeek")
    @Mapping(target="date", source="dt", qualifiedByName = "convertEpochToStringDate")
    @Mapping(target="minStatusId", source="weather", qualifiedByName = "getStatusId4DayStatus")
    @Mapping(target="maxStatusId", source="weather", qualifiedByName = "getStatusId4DayStatus")
    @Mapping(target="minTemp", source="temp.min")
    @Mapping(target="maxTemp", source="temp.max")
    @Mapping(target="windSpeed", source="speed")
    DayForecast map(ApiDayStatus.DayStatusItem dayStatusItem);

    List<DayForecast> map(List<ApiDayStatus.DayStatusItem> list);

    @Named("getStatusId4CurrenStatus")
    default Integer getStatusId4CurrenStatus(List<ApiCurrentStatus.WeatherItem> weather) {
        return !ObjectUtils.isEmpty(weather) && !ObjectUtils.isEmpty(weather.get(0))
                ? weather.get(0).getId()
                : null;
    }

    @Named("resolveDayOfWeek")
    default String resolveDayOfWeek(long epochDate) {
        String dayOfWeek;
        if (DateTimeUtil.isToday(epochDate)) {
            dayOfWeek = "Today";
        } else if (DateTimeUtil.isTomorrow(epochDate)) {
            dayOfWeek = "Tomorrow";
        } else {
            dayOfWeek = DateTimeUtil.getDayOfWeekFromEpochDate(epochDate);
        }
        return dayOfWeek;
    }

    @Named("convertEpochToStringDate")
    default String convertEpochToStringDate(long epochDate) {
        return DateTimeUtil.convertEpochToStringDate(epochDate);
    }

    @Named("getStatusId4DayStatus")
    default Integer getStatusId4DayStatus(List<ApiDayStatus.WeatherItem> weather) {
        return !ObjectUtils.isEmpty(weather) && !ObjectUtils.isEmpty(weather.get(0))
                ? weather.get(0).getId()
                : null;
    }

    @Named("getListFromApiDayStatus")
    default List<ApiDayStatus.DayStatusItem> getListFromApiDayStatus(ApiDayStatus apiDayStatus) {
        return apiDayStatus.getList();
    }
}
