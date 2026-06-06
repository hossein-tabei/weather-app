package demo.application.backend.app;

import demo.application.backend.LocationTestHelper;
import demo.application.backend.weather.LocationMapperImpl;
import demo.application.backend.weather.model.*;
import demo.application.backend.weather.repository.LocationRepository;
import demo.application.backend.weather.repository.WeatherRepository;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = WeatherController.class)
@ContextConfiguration(classes = {WeatherController.class, WeatherMapperImpl.class, LocationMapperImpl.class})
public class WeatherControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private WeatherRepository weatherRepository;
    @MockBean private LocationRepository locationRepository;

    //------------------------------------------------------------------------------------------------------------------
    // searchLocation
    @Test
    @DisplayName("'searchLocation' returns locations list with given search term")
    void searchLocation_returnsLocationsList_withGivenSearchTerm() throws Exception {
        // Arrange
        String searchTerm = "searchTerm";

        double lat = 1d, lon = 2d;
        String city = "city", state = "state", country = "country";
        Location location = LocationTestHelper.getLocation(lat, lon, city, state, country);

        when(locationRepository.searchLocation(anyString())).thenReturn(List.of(location));

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/location/search")
                .queryParam("searchTerm", searchTerm)
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(locationRepository).searchLocation(searchTerm);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        resultActions.andExpect(jsonPath("$").isArray());
        resultActions.andExpect(jsonPath("$", hasSize(1)));
        resultActions.andExpect(jsonPath("$[0].lat").value(lat));
        resultActions.andExpect(jsonPath("$[0].lon").value(lon));
        resultActions.andExpect(jsonPath("$[0].city").value(city));
        resultActions.andExpect(jsonPath("$[0].state").value(state));
        resultActions.andExpect(jsonPath("$[0].country").value(country));
    }

    @Test
    @DisplayName("'searchLocation' returns error message when search term is not present in query param")
    void searchLocation_returnsErrorMessage_whenSearchTermIsNotPresentInQueryParam() throws Exception {
        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/location/search")
//                .queryParam("searchTerm", searchTerm)
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(locationRepository, never()).searchLocation(any());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'searchTerm' is not present.", response.getErrorMessage());
    }

    @Test
    @DisplayName("'searchLocation' throws ConstraintViolationException when given search term is empty")
    void searchLocation_throws_ConstraintViolationException_whenGivenSearchTermIsEmpty() {
        // Act
        ServletException exception = assertThrows(ServletException.class,
                () -> mockMvc.perform(get("/api/v1/weather/location/search")
                        .queryParam("searchTerm", "")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
        );

        // Assert
        verify(locationRepository, never()).searchLocation(any());
        assertThat(exception).hasCauseInstanceOf(ConstraintViolationException.class);
        assertThat(exception).hasRootCauseMessage("searchLocation.searchTerm: must not be blank");
    }

    //------------------------------------------------------------------------------------------------------------------
    // currentWeather
    @Test
    @DisplayName("'currentWeather' returns current weather info with given lat and lon input param")
    void currentWeather_returnsCurrentWeatherInfo_withGivenLatLonInputParam() throws Exception {
        // Arrange
        double lat = 1d, lon = 2d;

        // ApiCurrentStatus.Main
        Double temperature = 19d,  minTemp = 15d,  maxTemp = 25d;
        Integer humidity = 35,  realFeel = 14;

        // ApiCurrentStatus
        String city = "city";
        String country = "country";
        Integer statusId = 405, chanceOfRain = 20;

        CurrentWeather currentWeather = WeatherTestHelper.getCurrentWeather(city, country, statusId, temperature,
                minTemp, maxTemp, humidity, realFeel, chanceOfRain);
        Weather weather = new Weather();
        weather.setCurrentWeather(currentWeather);

        when(weatherRepository.getWeatherByLocation(anyDouble(), anyDouble())).thenReturn(weather);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/now")
                .queryParam("lat", String.valueOf(lat))
                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository).getWeatherByLocation(lat, lon);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        resultActions.andExpect(jsonPath("$").isMap());
        resultActions.andExpect(jsonPath("$.city").value(city));
        resultActions.andExpect(jsonPath("$.country").value(country));
        resultActions.andExpect(jsonPath("$.statusId").value(statusId));
        resultActions.andExpect(jsonPath("$.temperature").value(temperature));
        resultActions.andExpect(jsonPath("$.minTemp").value(minTemp));
        resultActions.andExpect(jsonPath("$.maxTemp").value(maxTemp));
        resultActions.andExpect(jsonPath("$.humidity").value(humidity));
        resultActions.andExpect(jsonPath("$.realFeel").value(realFeel));
        resultActions.andExpect(jsonPath("$.chanceOfRain").value(chanceOfRain));
    }

    @Test
    @DisplayName("'currentWeather' returns error message when input lat is not present in query param")
    void currentWeather_returnsErrorMessage_whenInputLatIsNotPresentInQueryParam() throws Exception {
        // Arrange
        double lon = 1;

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/now")
//                .queryParam("lat", String.valueOf(lat))
                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository, never()).getWeatherByLocation(anyDouble(), anyDouble());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'lat' is not present.", response.getErrorMessage());
    }

    @Test
    @DisplayName("'currentWeather' returns error message when input lon is not present in query param")
    void currentWeather_returnsErrorMessage_whenInputLonIsNotPresentInQueryParam() throws Exception {
        // Arrange
        double lat = 1;

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/now")
                .queryParam("lat", String.valueOf(lat))
//                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository, never()).getWeatherByLocation(anyDouble(), anyDouble());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'lon' is not present.", response.getErrorMessage());
    }

    //------------------------------------------------------------------------------------------------------------------
    // next5DaysForecast
    @Test
    @DisplayName("'next5DaysForecast' returns next 5 Days Forecast list with given lat and lon input param")
    void next5DaysForecast_returnsNext5DaysForecastList_withGivenLatLonInputParam() throws Exception {
        // Arrange
        double lat = 1d, lon = 2d;

        // DayForecast
        String dayOfWeek1 = "TODAY", date1 = "2026/03/13";
        int minStatusId1 = 105, maxStatusId1 = 110;
        double minTemp1 = 10d, maxTemp1 = 15d, windSpeed1 = 18;

        String dayOfWeek2 = "TOMORROW", date2 = "2026/03/14";
        int minStatusId2 = 205, maxStatusId2 = 210;
        double minTemp2 = 11d, maxTemp2 = 16d, windSpeed2 = 19;

        String dayOfWeek3 = "SUNDAY", date3 = "2026/03/15";
        int minStatusId3 = 305, maxStatusId3 = 310;
        double minTemp3 = 12d, maxTemp3 = 17d, windSpeed3 = 20;

        String dayOfWeek4 = "MONDAY", date4 = "2026/03/16";
        int minStatusId4 = 405, maxStatusId4 = 410;
        double minTemp4 = 13d, maxTemp4 = 18d, windSpeed4 = 21;

        String dayOfWeek5 = "TUESDAY", date5 = "2026/03/17";
        int minStatusId5 = 505, maxStatusId5 = 510;
        double minTemp5 = 14d, maxTemp5 = 19d, windSpeed5 = 22;

        DayForecast day1 = WeatherTestHelper.getDayForecast(dayOfWeek1, date1, minStatusId1, maxStatusId1, minTemp1, maxTemp1, windSpeed1);
        DayForecast day2 = WeatherTestHelper.getDayForecast(dayOfWeek2, date2, minStatusId2, maxStatusId2, minTemp2, maxTemp2, windSpeed2);
        DayForecast day3 = WeatherTestHelper.getDayForecast(dayOfWeek3, date3, minStatusId3, maxStatusId3, minTemp3, maxTemp3, windSpeed3);
        DayForecast day4 = WeatherTestHelper.getDayForecast(dayOfWeek4, date4, minStatusId4, maxStatusId4, minTemp4, maxTemp4, windSpeed4);
        DayForecast day5 = WeatherTestHelper.getDayForecast(dayOfWeek5, date5, minStatusId5, maxStatusId5, minTemp5, maxTemp5, windSpeed5);

        Weather weather = new Weather();
        weather.set_5DaysForecast(List.of(day1, day2, day3, day4, day5));

        when(weatherRepository.getWeatherByLocation(anyDouble(), anyDouble())).thenReturn(weather);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/forecast/next5days")
                .queryParam("lat", String.valueOf(lat))
                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository).getWeatherByLocation(lat, lon);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        resultActions.andExpect(jsonPath("$").isArray());
        resultActions.andExpect(jsonPath("$", hasSize(5)));
        resultActions.andExpect(jsonPath("$[0].dayOfWeek").value(dayOfWeek1));
        resultActions.andExpect(jsonPath("$[0].date").value(date1));
        resultActions.andExpect(jsonPath("$[0].minStatusId").value(minStatusId1));
        resultActions.andExpect(jsonPath("$[0].maxStatusId").value(maxStatusId1));
        resultActions.andExpect(jsonPath("$[0].minTemp").value(minTemp1));
        resultActions.andExpect(jsonPath("$[0].maxTemp").value(maxTemp1));
        resultActions.andExpect(jsonPath("$[0].windSpeed").value(windSpeed1));

        resultActions.andExpect(jsonPath("$[1].dayOfWeek").value(dayOfWeek2));
        resultActions.andExpect(jsonPath("$[1].date").value(date2));
        resultActions.andExpect(jsonPath("$[1].minStatusId").value(minStatusId2));
        resultActions.andExpect(jsonPath("$[1].maxStatusId").value(maxStatusId2));
        resultActions.andExpect(jsonPath("$[1].minTemp").value(minTemp2));
        resultActions.andExpect(jsonPath("$[1].maxTemp").value(maxTemp2));
        resultActions.andExpect(jsonPath("$[1].windSpeed").value(windSpeed2));

        resultActions.andExpect(jsonPath("$[2].dayOfWeek").value(dayOfWeek3));
        resultActions.andExpect(jsonPath("$[2].date").value(date3));
        resultActions.andExpect(jsonPath("$[2].minStatusId").value(minStatusId3));
        resultActions.andExpect(jsonPath("$[2].maxStatusId").value(maxStatusId3));
        resultActions.andExpect(jsonPath("$[2].minTemp").value(minTemp3));
        resultActions.andExpect(jsonPath("$[2].maxTemp").value(maxTemp3));
        resultActions.andExpect(jsonPath("$[2].windSpeed").value(windSpeed3));

        resultActions.andExpect(jsonPath("$[3].dayOfWeek").value(dayOfWeek4));
        resultActions.andExpect(jsonPath("$[3].date").value(date4));
        resultActions.andExpect(jsonPath("$[3].minStatusId").value(minStatusId4));
        resultActions.andExpect(jsonPath("$[3].maxStatusId").value(maxStatusId4));
        resultActions.andExpect(jsonPath("$[3].minTemp").value(minTemp4));
        resultActions.andExpect(jsonPath("$[3].maxTemp").value(maxTemp4));
        resultActions.andExpect(jsonPath("$[3].windSpeed").value(windSpeed4));

        resultActions.andExpect(jsonPath("$[4].dayOfWeek").value(dayOfWeek5));
        resultActions.andExpect(jsonPath("$[4].date").value(date5));
        resultActions.andExpect(jsonPath("$[4].minStatusId").value(minStatusId5));
        resultActions.andExpect(jsonPath("$[4].maxStatusId").value(maxStatusId5));
        resultActions.andExpect(jsonPath("$[4].minTemp").value(minTemp5));
        resultActions.andExpect(jsonPath("$[4].maxTemp").value(maxTemp5));
        resultActions.andExpect(jsonPath("$[4].windSpeed").value(windSpeed5));
    }

    @Test
    @DisplayName("'next5DaysForecast' returns error message when input lat is not present in query param")
    void next5DaysForecast_returnsErrorMessage_whenInputLatIsNotPresentInQueryParam() throws Exception {
        // Arrange
        double lon = 1;

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/forecast/next5days")
//                .queryParam("lat", String.valueOf(lat))
                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository, never()).getWeatherByLocation(anyDouble(), anyDouble());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'lat' is not present.", response.getErrorMessage());
    }

    @Test
    @DisplayName("'next5DaysForecast' returns error message when input lon is not present in query param")
    void next5DaysForecast_returnsErrorMessage_whenInputLonIsNotPresentInQueryParam() throws Exception {
        // Arrange
        double lat = 1;

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/forecast/next5days")
                .queryParam("lat", String.valueOf(lat))
//                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository, never()).getWeatherByLocation(anyDouble(), anyDouble());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'lon' is not present.", response.getErrorMessage());
    }

    //------------------------------------------------------------------------------------------------------------------
    // next24HoursForecast
    @Test
    @DisplayName("'next24HoursForecast' returns next 24 hours forecast list with given lat and lon input param")
    void next24HoursForecast_returnsNext24HoursForecastList_withGivenLatLonInputParam() throws Exception {
        // Arrange
        double lat = 1d, lon = 2d;

        // HourForecast
        int temperature1 = 10, statusId1 = 105;
        float windSpeed1 = 12f;
        String hourOfDay1 = "Now";

        int temperature2 = 11, statusId2 = 106;
        float windSpeed2 = 13f;
        String hourOfDay2 = "00:00";

        int temperature3 = 12, statusId3 = 107;
        float windSpeed3 = 14f;
        String hourOfDay3 = "01:00";

        int temperature4 = 13, statusId4 = 108;
        float windSpeed4 = 15f;
        String hourOfDay4 = "02:00";

        int temperature5 = 14, statusId5 = 109;
        float windSpeed5 = 16f;
        String hourOfDay5 = "03:00";

        int temperature6 = 15, statusId6 = 110;
        float windSpeed6 = 17f;
        String hourOfDay6 = "04:00";

        int temperature7 = 16, statusId7 = 111;
        float windSpeed7 = 18f;
        String hourOfDay7 = "05:00";

        int temperature8 = 17, statusId8 = 112;
        float windSpeed8 = 19f;
        String hourOfDay8 = "06:00";

        int temperature9 = 18, statusId9 = 113;
        float windSpeed9 = 20f;
        String hourOfDay9 = "07:00";

        int temperature10 = 19, statusId10 = 114;
        float windSpeed10 = 21f;
        String hourOfDay10 = "08:00";

        int temperature11 = 20, statusId11 = 115;
        float windSpeed11 = 22f;
        String hourOfDay11 = "09:00";

        int temperature12 = 21, statusId12 = 116;
        float windSpeed12 = 23f;
        String hourOfDay12 = "10:00";

        int temperature13 = 22, statusId13 = 117;
        float windSpeed13 = 24f;
        String hourOfDay13 = "11:00";

        int temperature14 = 23, statusId14 = 118;
        float windSpeed14 = 25f;
        String hourOfDay14 = "12:00";

        int temperature15 = 24, statusId15 = 119;
        float windSpeed15 = 26f;
        String hourOfDay15 = "13:00";

        int temperature16 = 25, statusId16 = 120;
        float windSpeed16 = 27f;
        String hourOfDay16 = "14:00";

        int temperature17 = 26, statusId17 = 121;
        float windSpeed17 = 28f;
        String hourOfDay17 = "15:00";

        int temperature18 = 27, statusId18 = 122;
        float windSpeed18 = 29f;
        String hourOfDay18 = "16:00";

        int temperature19 = 28, statusId19 = 123;
        float windSpeed19 = 30f;
        String hourOfDay19 = "17:00";

        int temperature20 = 29, statusId20 = 124;
        float windSpeed20 = 31f;
        String hourOfDay20 = "18:00";

        int temperature21 = 30, statusId21 = 125;
        float windSpeed21 = 32f;
        String hourOfDay21 = "19:00";

        int temperature22 = 31, statusId22 = 126;
        float windSpeed22 = 33f;
        String hourOfDay22 = "20:00";

        int temperature23 = 32, statusId23 = 127;
        float windSpeed23 = 34f;
        String hourOfDay23 = "21:00";

        int temperature24 = 33, statusId24 = 128;
        float windSpeed24 = 35f;
        String hourOfDay24 = "22:00";

        HourForecast hour1 = WeatherTestHelper.getHourForecast(temperature1, statusId1, windSpeed1, hourOfDay1);
        HourForecast hour2 = WeatherTestHelper.getHourForecast(temperature2, statusId2, windSpeed2, hourOfDay2);
        HourForecast hour3 = WeatherTestHelper.getHourForecast(temperature3, statusId3, windSpeed3, hourOfDay3);
        HourForecast hour4 = WeatherTestHelper.getHourForecast(temperature4, statusId4, windSpeed4, hourOfDay4);
        HourForecast hour5 = WeatherTestHelper.getHourForecast(temperature5, statusId5, windSpeed5, hourOfDay5);
        HourForecast hour6 = WeatherTestHelper.getHourForecast(temperature6, statusId6, windSpeed6, hourOfDay6);
        HourForecast hour7 = WeatherTestHelper.getHourForecast(temperature7, statusId7, windSpeed7, hourOfDay7);
        HourForecast hour8 = WeatherTestHelper.getHourForecast(temperature8, statusId8, windSpeed8, hourOfDay8);
        HourForecast hour9 = WeatherTestHelper.getHourForecast(temperature9, statusId9, windSpeed9, hourOfDay9);
        HourForecast hour10 = WeatherTestHelper.getHourForecast(temperature10, statusId10, windSpeed10, hourOfDay10);
        HourForecast hour11 = WeatherTestHelper.getHourForecast(temperature11, statusId11, windSpeed11, hourOfDay11);
        HourForecast hour12 = WeatherTestHelper.getHourForecast(temperature12, statusId12, windSpeed12, hourOfDay12);
        HourForecast hour13 = WeatherTestHelper.getHourForecast(temperature13, statusId13, windSpeed13, hourOfDay13);
        HourForecast hour14 = WeatherTestHelper.getHourForecast(temperature14, statusId14, windSpeed14, hourOfDay14);
        HourForecast hour15 = WeatherTestHelper.getHourForecast(temperature15, statusId15, windSpeed15, hourOfDay15);
        HourForecast hour16 = WeatherTestHelper.getHourForecast(temperature16, statusId16, windSpeed16, hourOfDay16);
        HourForecast hour17 = WeatherTestHelper.getHourForecast(temperature17, statusId17, windSpeed17, hourOfDay17);
        HourForecast hour18 = WeatherTestHelper.getHourForecast(temperature18, statusId18, windSpeed18, hourOfDay18);
        HourForecast hour19 = WeatherTestHelper.getHourForecast(temperature19, statusId19, windSpeed19, hourOfDay19);
        HourForecast hour20 = WeatherTestHelper.getHourForecast(temperature20, statusId20, windSpeed20, hourOfDay20);
        HourForecast hour21 = WeatherTestHelper.getHourForecast(temperature21, statusId21, windSpeed21, hourOfDay21);
        HourForecast hour22 = WeatherTestHelper.getHourForecast(temperature22, statusId22, windSpeed22, hourOfDay22);
        HourForecast hour23 = WeatherTestHelper.getHourForecast(temperature23, statusId23, windSpeed23, hourOfDay23);
        HourForecast hour24 = WeatherTestHelper.getHourForecast(temperature24, statusId24, windSpeed24, hourOfDay24);


        Weather weather = new Weather();
        weather.set_24HoursForecast(List.of(hour1, hour2, hour3, hour4, hour5, hour6, hour7, hour8, hour9, hour10,
                hour11, hour12, hour13, hour14, hour15, hour16, hour17, hour18, hour19, hour20, hour21, hour22, hour23, hour24));

        when(weatherRepository.getWeatherByLocation(anyDouble(), anyDouble())).thenReturn(weather);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/forecast/next24hours")
                .queryParam("lat", String.valueOf(lat))
                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository).getWeatherByLocation(lat, lon);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        resultActions.andExpect(jsonPath("$").isArray());
        resultActions.andExpect(jsonPath("$", hasSize(24)));
        resultActions.andExpect(jsonPath("$[0].temperature").value(temperature1));
        resultActions.andExpect(jsonPath("$[0].statusId").value(statusId1));
        resultActions.andExpect(jsonPath("$[0].windSpeed").value(windSpeed1));
        resultActions.andExpect(jsonPath("$[0].hourOfDay").value(hourOfDay1));

        resultActions.andExpect(jsonPath("$[1].temperature").value(temperature2));
        resultActions.andExpect(jsonPath("$[1].statusId").value(statusId2));
        resultActions.andExpect(jsonPath("$[1].windSpeed").value(windSpeed2));
        resultActions.andExpect(jsonPath("$[1].hourOfDay").value(hourOfDay2));

        resultActions.andExpect(jsonPath("$[2].temperature").value(temperature3));
        resultActions.andExpect(jsonPath("$[2].statusId").value(statusId3));
        resultActions.andExpect(jsonPath("$[2].windSpeed").value(windSpeed3));
        resultActions.andExpect(jsonPath("$[2].hourOfDay").value(hourOfDay3));

        resultActions.andExpect(jsonPath("$[3].temperature").value(temperature4));
        resultActions.andExpect(jsonPath("$[3].statusId").value(statusId4));
        resultActions.andExpect(jsonPath("$[3].windSpeed").value(windSpeed4));
        resultActions.andExpect(jsonPath("$[3].hourOfDay").value(hourOfDay4));

        resultActions.andExpect(jsonPath("$[4].temperature").value(temperature5));
        resultActions.andExpect(jsonPath("$[4].statusId").value(statusId5));
        resultActions.andExpect(jsonPath("$[4].windSpeed").value(windSpeed5));
        resultActions.andExpect(jsonPath("$[4].hourOfDay").value(hourOfDay5));

        resultActions.andExpect(jsonPath("$[5].temperature").value(temperature6));
        resultActions.andExpect(jsonPath("$[5].statusId").value(statusId6));
        resultActions.andExpect(jsonPath("$[5].windSpeed").value(windSpeed6));
        resultActions.andExpect(jsonPath("$[5].hourOfDay").value(hourOfDay6));

        resultActions.andExpect(jsonPath("$[6].temperature").value(temperature7));
        resultActions.andExpect(jsonPath("$[6].statusId").value(statusId7));
        resultActions.andExpect(jsonPath("$[6].windSpeed").value(windSpeed7));
        resultActions.andExpect(jsonPath("$[6].hourOfDay").value(hourOfDay7));

        resultActions.andExpect(jsonPath("$[7].temperature").value(temperature8));
        resultActions.andExpect(jsonPath("$[7].statusId").value(statusId8));
        resultActions.andExpect(jsonPath("$[7].windSpeed").value(windSpeed8));
        resultActions.andExpect(jsonPath("$[7].hourOfDay").value(hourOfDay8));

        resultActions.andExpect(jsonPath("$[8].temperature").value(temperature9));
        resultActions.andExpect(jsonPath("$[8].statusId").value(statusId9));
        resultActions.andExpect(jsonPath("$[8].windSpeed").value(windSpeed9));
        resultActions.andExpect(jsonPath("$[8].hourOfDay").value(hourOfDay9));

        resultActions.andExpect(jsonPath("$[9].temperature").value(temperature10));
        resultActions.andExpect(jsonPath("$[9].statusId").value(statusId10));
        resultActions.andExpect(jsonPath("$[9].windSpeed").value(windSpeed10));
        resultActions.andExpect(jsonPath("$[9].hourOfDay").value(hourOfDay10));

        resultActions.andExpect(jsonPath("$[10].temperature").value(temperature11));
        resultActions.andExpect(jsonPath("$[10].statusId").value(statusId11));
        resultActions.andExpect(jsonPath("$[10].windSpeed").value(windSpeed11));
        resultActions.andExpect(jsonPath("$[10].hourOfDay").value(hourOfDay11));

        resultActions.andExpect(jsonPath("$[11].temperature").value(temperature12));
        resultActions.andExpect(jsonPath("$[11].statusId").value(statusId12));
        resultActions.andExpect(jsonPath("$[11].windSpeed").value(windSpeed12));
        resultActions.andExpect(jsonPath("$[11].hourOfDay").value(hourOfDay12));

        resultActions.andExpect(jsonPath("$[12].temperature").value(temperature13));
        resultActions.andExpect(jsonPath("$[12].statusId").value(statusId13));
        resultActions.andExpect(jsonPath("$[12].windSpeed").value(windSpeed13));
        resultActions.andExpect(jsonPath("$[12].hourOfDay").value(hourOfDay13));

        resultActions.andExpect(jsonPath("$[13].temperature").value(temperature14));
        resultActions.andExpect(jsonPath("$[13].statusId").value(statusId14));
        resultActions.andExpect(jsonPath("$[13].windSpeed").value(windSpeed14));
        resultActions.andExpect(jsonPath("$[13].hourOfDay").value(hourOfDay14));

        resultActions.andExpect(jsonPath("$[14].temperature").value(temperature15));
        resultActions.andExpect(jsonPath("$[14].statusId").value(statusId15));
        resultActions.andExpect(jsonPath("$[14].windSpeed").value(windSpeed15));
        resultActions.andExpect(jsonPath("$[14].hourOfDay").value(hourOfDay15));

        resultActions.andExpect(jsonPath("$[15].temperature").value(temperature16));
        resultActions.andExpect(jsonPath("$[15].statusId").value(statusId16));
        resultActions.andExpect(jsonPath("$[15].windSpeed").value(windSpeed16));
        resultActions.andExpect(jsonPath("$[15].hourOfDay").value(hourOfDay16));

        resultActions.andExpect(jsonPath("$[16].temperature").value(temperature17));
        resultActions.andExpect(jsonPath("$[16].statusId").value(statusId17));
        resultActions.andExpect(jsonPath("$[16].windSpeed").value(windSpeed17));
        resultActions.andExpect(jsonPath("$[16].hourOfDay").value(hourOfDay17));

        resultActions.andExpect(jsonPath("$[17].temperature").value(temperature18));
        resultActions.andExpect(jsonPath("$[17].statusId").value(statusId18));
        resultActions.andExpect(jsonPath("$[17].windSpeed").value(windSpeed18));
        resultActions.andExpect(jsonPath("$[17].hourOfDay").value(hourOfDay18));

        resultActions.andExpect(jsonPath("$[18].temperature").value(temperature19));
        resultActions.andExpect(jsonPath("$[18].statusId").value(statusId19));
        resultActions.andExpect(jsonPath("$[18].windSpeed").value(windSpeed19));
        resultActions.andExpect(jsonPath("$[18].hourOfDay").value(hourOfDay19));

        resultActions.andExpect(jsonPath("$[19].temperature").value(temperature20));
        resultActions.andExpect(jsonPath("$[19].statusId").value(statusId20));
        resultActions.andExpect(jsonPath("$[19].windSpeed").value(windSpeed20));
        resultActions.andExpect(jsonPath("$[19].hourOfDay").value(hourOfDay20));

        resultActions.andExpect(jsonPath("$[20].temperature").value(temperature21));
        resultActions.andExpect(jsonPath("$[20].statusId").value(statusId21));
        resultActions.andExpect(jsonPath("$[20].windSpeed").value(windSpeed21));
        resultActions.andExpect(jsonPath("$[20].hourOfDay").value(hourOfDay21));

        resultActions.andExpect(jsonPath("$[21].temperature").value(temperature22));
        resultActions.andExpect(jsonPath("$[21].statusId").value(statusId22));
        resultActions.andExpect(jsonPath("$[21].windSpeed").value(windSpeed22));
        resultActions.andExpect(jsonPath("$[21].hourOfDay").value(hourOfDay22));

        resultActions.andExpect(jsonPath("$[22].temperature").value(temperature23));
        resultActions.andExpect(jsonPath("$[22].statusId").value(statusId23));
        resultActions.andExpect(jsonPath("$[22].windSpeed").value(windSpeed23));
        resultActions.andExpect(jsonPath("$[22].hourOfDay").value(hourOfDay23));

        resultActions.andExpect(jsonPath("$[23].temperature").value(temperature24));
        resultActions.andExpect(jsonPath("$[23].statusId").value(statusId24));
        resultActions.andExpect(jsonPath("$[23].windSpeed").value(windSpeed24));
        resultActions.andExpect(jsonPath("$[23].hourOfDay").value(hourOfDay24));
    }

    @Test
    @DisplayName("'next24HoursForecast' returns error message when input lat is not present in query param")
    void next24HoursForecast_returnsErrorMessage_whenInputLatIsNotPresentInQueryParam() throws Exception {
        // Arrange
        double lon = 1;

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/forecast/next24hours")
//                .queryParam("lat", String.valueOf(lat))
                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository, never()).getWeatherByLocation(anyDouble(), anyDouble());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'lat' is not present.", response.getErrorMessage());
    }

    @Test
    @DisplayName("'next24HoursForecast' returns error message when input lon is not present in query param")
    void next24HoursForecast_returnsErrorMessage_whenInputLonIsNotPresentInQueryParam() throws Exception {
        // Arrange
        double lat = 1;

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/forecast/next24hours")
                .queryParam("lat", String.valueOf(lat))
//                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository, never()).getWeatherByLocation(anyDouble(), anyDouble());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'lon' is not present.", response.getErrorMessage());
    }

    //------------------------------------------------------------------------------------------------------------------
    // airQualityIndex
    @Test
    @DisplayName("'airQualityIndex' returns air quality index with given lat and lon input param")
    void airQualityIndex_returnsAirQualityIndex_withGivenLatLonInputParam() throws Exception {
        // Arrange
        double lat = 1d, lon = 2d;

        int numericlevel = 28;
        String verbalLevel = "verbalLevel", description = "description", publicationInfo = "publicationInfo";

        // PolutionIndex
        String chemicalName1 = "PM2.5";
        float amount1 = 28.9f;

        String chemicalName2 = "PM10";
        float amount2 = 16.4f;

        String chemicalName3 = "SO2";
        float amount3 = 3f;

        String chemicalName4 = "NO2";
        float amount4 = 19f;

        String chemicalName5 = "O3";
        float amount5 = 12.7f;

        String chemicalName6 = "CO";
        float amount6 = 2.7f;

        PolutionIndex index1 = new PolutionIndex(chemicalName1, amount1);
        PolutionIndex index2 = new PolutionIndex(chemicalName2, amount2);
        PolutionIndex index3 = new PolutionIndex(chemicalName3, amount3);
        PolutionIndex index4 = new PolutionIndex(chemicalName4, amount4);
        PolutionIndex index5 = new PolutionIndex(chemicalName5, amount5);
        PolutionIndex index6 = new PolutionIndex(chemicalName6, amount6);
        AirQualityIndex airQualityIndex = WeatherTestHelper.getAirQualityIndex(numericlevel, verbalLevel, description, publicationInfo, List.of(index1, index2, index3, index4, index5, index6));

        Weather weather = new Weather();
        weather.setAirQualityIndex(airQualityIndex);

        when(weatherRepository.getWeatherByLocation(anyDouble(), anyDouble())).thenReturn(weather);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/aqi")
                .queryParam("lat", String.valueOf(lat))
                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository).getWeatherByLocation(lat, lon);
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        resultActions.andExpect(jsonPath("$").isMap());
        resultActions.andExpect(jsonPath("$.numericlevel").value(numericlevel));
        resultActions.andExpect(jsonPath("$.verbalLevel").value(verbalLevel));
        resultActions.andExpect(jsonPath("$.description").value(description));
        resultActions.andExpect(jsonPath("$.publicationInfo").value(publicationInfo));

        resultActions.andExpect(jsonPath("$.polutionIndexes").isArray());
        resultActions.andExpect(jsonPath("$.polutionIndexes", hasSize(6)));
        resultActions.andExpect(jsonPath("$.polutionIndexes[0].chemicalName").value(chemicalName1));
        resultActions.andExpect(jsonPath("$.polutionIndexes[0].amount").value(amount1));

        resultActions.andExpect(jsonPath("$.polutionIndexes[1].chemicalName").value(chemicalName2));
        resultActions.andExpect(jsonPath("$.polutionIndexes[1].amount").value(amount2));

        resultActions.andExpect(jsonPath("$.polutionIndexes[2].chemicalName").value(chemicalName3));
        resultActions.andExpect(jsonPath("$.polutionIndexes[2].amount").value(amount3));

        resultActions.andExpect(jsonPath("$.polutionIndexes[3].chemicalName").value(chemicalName4));
        resultActions.andExpect(jsonPath("$.polutionIndexes[3].amount").value(amount4));

        resultActions.andExpect(jsonPath("$.polutionIndexes[4].chemicalName").value(chemicalName5));
        resultActions.andExpect(jsonPath("$.polutionIndexes[4].amount").value(amount5));

        resultActions.andExpect(jsonPath("$.polutionIndexes[5].chemicalName").value(chemicalName6));
        resultActions.andExpect(jsonPath("$.polutionIndexes[5].amount").value(amount6));
    }

    @Test
    @DisplayName("'airQualityIndex' returns error message when input lat is not present in query param")
    void airQualityIndex_returnsErrorMessage_whenInputLatIsNotPresentInQueryParam() throws Exception {
        // Arrange
        double lon = 1;

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/aqi")
//                .queryParam("lat", String.valueOf(lat))
                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository, never()).getWeatherByLocation(anyDouble(), anyDouble());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'lat' is not present.", response.getErrorMessage());
    }

    @Test
    @DisplayName("'airQualityIndex' returns error message when input lon is not present in query param")
    void airQualityIndex_returnsErrorMessage_whenInputLonIsNotPresentInQueryParam() throws Exception {
        // Arrange
        double lat = 1;

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/weather/aqi")
                .queryParam("lat", String.valueOf(lat))
//                .queryParam("lon", String.valueOf(lon))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // Assert
        verify(weatherRepository, never()).getWeatherByLocation(anyDouble(), anyDouble());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Required parameter 'lon' is not present.", response.getErrorMessage());
    }

}
