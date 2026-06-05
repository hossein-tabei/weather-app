package demo.application.backend.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import demo.application.backend.config.AppConfig;
import demo.application.backend.infra.model.ApiCurrentStatus;
import demo.application.backend.infra.model.ApiDayStatus;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ForecastRemoteApi.class, AppConfig.class, WebClient.class},
        properties = {"remoteConfig.apiKey:apiKey", "remoteConfig.host:http://example.com", "remoteConfig.forecast.path.currentStatus:/weather/current/status",
                    "remoteConfig.forecast.path.dailyForecast:/weather/forecast/daily", "weather.units:metric", "weather.forecast.dailyCount:5"}
)
public class ForecastRemoteApiTest {

    @Autowired private IForecastRemoteApi forecastRemoteApi;
    @Autowired private AppConfig appConfig;
    @MockBean private WebClient client;
    @Mock private WebClient.RequestHeadersUriSpec uriSpecMock;
    @Mock private WebClient.RequestHeadersSpec specMock;
    @Mock private WebClient.ResponseSpec responseSpecMock;
    @Mock private Mono<ResponseEntity<String>> monoResult;

    private static final Gson gson = new Gson();

    // -----------------------------------------------------------------------------------------------------------------
    // currentStatus
    @Test
    @DisplayName("'currentStatus' returns a current weather status when all params are valid")
    void currentStatus_returns_currentWeatherStatus_when_allParamsAreValid() {
        // Arrange
        double lat = 1, lon = 1;
        String apiKey = appConfig.getApiKey(), host = appConfig.getHost(), currentWeatherPath = appConfig.getCurrentWeatherPath(), units = appConfig.getUnits();

        // ApiCurrentStatus.Main
        Double temp = 19d,  temp_min = 15d,  temp_max = 25d;
        Integer humidity = 35,  feels_like = 14;

        // ApiCurrentStatus
        String name = "Oakland";
        String country = "US";
        Integer weatherId = 405;

        String uri = StringUtils.join(host, currentWeatherPath, "?lat=",lat, "&lon=",lon, "&units=",units, "&appid=",apiKey);
        ApiCurrentStatus.Main main = CurrentStatusTestHelper.getApiCurrentStatusMain(temp, temp_min, temp_max, humidity, feels_like);
        ApiCurrentStatus expectedResult = CurrentStatusTestHelper.getApiCurrentStatus(name, new ApiCurrentStatus.Sys(country), List.of(new ApiCurrentStatus.WeatherItem(weatherId)), main);

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenReturn(ResponseEntity.ok(gson.toJson(expectedResult)));

        // Act
        ApiCurrentStatus actualResult = forecastRemoteApi.currentStatus(lat, lon);

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("'currentStatus' throws WebClientResponseException when remote API returns error")
    void currentStatus_throws_WebClientResponseException_when_remoteApiReturnsError() {
        // Arrange
        double lat = 1, lon = 1;
        String apiKey = appConfig.getApiKey(), host = appConfig.getHost(), currentWeatherPath = appConfig.getCurrentWeatherPath(), units = appConfig.getUnits();
        String uri = StringUtils.join(host, currentWeatherPath, "?lat=",lat, "&lon=",lon, "&units=",units, "&appid=",apiKey);
        WebClientResponseException expectedException = WebClientResponseException.create(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error", new HttpHeaders(), new byte[0], null, null);

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenThrow(expectedException);

        // Act
        WebClientResponseException actualException = assertThrows(WebClientResponseException.class,
                () -> forecastRemoteApi.currentStatus(lat, lon));

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();
        assertEquals(expectedException, actualException);
    }

    @Test
    @DisplayName("'currentStatus' throws UnhandledException when unexpected error occurs during remote API call")
    void currentStatus_throws_UnhandledException_when_unexpectedErrorOccursDuringRemoteApiCall() {
        // Arrange
        double lat = 1, lon = 1;
        String apiKey = appConfig.getApiKey(), host = appConfig.getHost(), currentWeatherPath = appConfig.getCurrentWeatherPath(), units = appConfig.getUnits();
        String uri = StringUtils.join(host, currentWeatherPath, "?lat=",lat, "&lon=",lon, "&units=",units, "&appid=",apiKey);
        Exception expectedException = new NullPointerException("exception");

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenThrow(expectedException);

        // Act
        UnhandledException actualException = assertThrows(UnhandledException.class, () -> forecastRemoteApi.currentStatus(lat, lon));

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();
        assertEquals(expectedException, actualException.getCause());
        assertEquals("Unhandled Exception Occurred", actualException.getMessage());
    }

    @Test
    @DisplayName("'currentStatus' throws AppJsonProcessingException when remote API response body is not in json format")
    void currentStatus_throws_AppJsonProcessingException_when_remoteApiResponseBodyIsNotInJsonFormat() {
        // Arrange
        double lat = 1, lon = 1;
        String apiKey = appConfig.getApiKey(), host = appConfig.getHost(), currentWeatherPath = appConfig.getCurrentWeatherPath(), units = appConfig.getUnits();
        String uri = StringUtils.join(host, currentWeatherPath, "?lat=",lat, "&lon=",lon, "&units=",units, "&appid=",apiKey);

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenReturn(ResponseEntity.ok("Invalid Response Body"));

        // Act
        AppJsonProcessingException actualException = assertThrows(AppJsonProcessingException.class, () -> forecastRemoteApi.currentStatus(lat, lon));

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();
        assertInstanceOf(JsonProcessingException.class, actualException.getCause());
        assertEquals("Calling currentStatus api Failed. cause: Invalid json format", actualException.getMessage());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // next5DaysForecast
    @Test
    @DisplayName("'next5DaysForecast' returns next 5 days forecast when all params are valid")
    void next5DaysForecast_returns_next5DaysForecast_when_allParamsAreValid() {
        // Arrange
        double lat = 1, lon = 1;
        String apiKey = appConfig.getApiKey(), host = appConfig.getHost(), dailyForecastPath = appConfig.getDailyForecastPath(), units = appConfig.getUnits(), dailyResultCount = appConfig.getDailyResultCount();

        // ApiDayStatus.DayStatusItem
        Long dt1 = 1L;
        Integer weatherId1 = 105;
        Double minTemp1 = 1d,  maxTemp1 = 2d, speed1 = 1d;

        Long dt2 = 2L;
        Integer weatherId2 = 205;
        Double minTemp2 = 2d,  maxTemp2 = 3d, speed2 = 2d;

        Long dt3 = 3L;
        Integer weatherId3 = 305;
        Double minTemp3 = 3d,  maxTemp3 = 4d, speed3 = 3d;

        Long dt4 = 4L;
        Integer weatherId4 = 405;
        Double minTemp4 = 4d,  maxTemp4 = 5d, speed4 = 4d;

        Long dt5 = 5L;
        Integer weatherId5 = 505;
        Double minTemp5 = 5d,  maxTemp5 = 6d, speed5 = 5d;

        String uri = StringUtils.join(host, dailyForecastPath, "?lat=",lat, "&lon=",lon, "&units=",units, "&cnt=",Integer.valueOf(dailyResultCount), "&appid=",apiKey);
        ApiDayStatus.DayStatusItem day1 = DailyStatusTestHelper.getDayStatusItem(dt1, weatherId1, minTemp1, maxTemp1, speed1);
        ApiDayStatus.DayStatusItem day2 = DailyStatusTestHelper.getDayStatusItem(dt2, weatherId2, minTemp2, maxTemp2, speed2);
        ApiDayStatus.DayStatusItem day3 = DailyStatusTestHelper.getDayStatusItem(dt3, weatherId3, minTemp3, maxTemp3, speed3);
        ApiDayStatus.DayStatusItem day4 = DailyStatusTestHelper.getDayStatusItem(dt4, weatherId4, minTemp4, maxTemp4, speed4);
        ApiDayStatus.DayStatusItem day5 = DailyStatusTestHelper.getDayStatusItem(dt5, weatherId5, minTemp5, maxTemp5, speed5);
        ApiDayStatus expectedResult = DailyStatusTestHelper.getApiDayStatus(List.of(day1, day2, day3, day4, day5));

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenReturn(ResponseEntity.ok(gson.toJson(expectedResult)));

        // Act
        ApiDayStatus actualResult = forecastRemoteApi.next5DaysForecast(lat, lon);

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("'next5DaysForecast' throws WebClientResponseException when remote API returns error")
    void next5DaysForecast_throws_WebClientResponseException_when_remoteApiReturnsError() {
        // Arrange
        double lat = 1, lon = 1;
        String apiKey = appConfig.getApiKey(), host = appConfig.getHost(), dailyForecastPath = appConfig.getDailyForecastPath(), units = appConfig.getUnits(), dailyResultCount = appConfig.getDailyResultCount();
        String uri = StringUtils.join(host, dailyForecastPath, "?lat=",lat, "&lon=",lon, "&units=",units, "&cnt=",Integer.valueOf(dailyResultCount), "&appid=",apiKey);
        WebClientResponseException expectedException = WebClientResponseException.create(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error", new HttpHeaders(), new byte[0], null, null);

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenThrow(expectedException);

        // Act
        WebClientResponseException actualException = assertThrows(WebClientResponseException.class,
                () -> forecastRemoteApi.next5DaysForecast(lat, lon));

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();
        assertEquals(expectedException, actualException);
    }

    @Test
    @DisplayName("'next5DaysForecast' throws UnhandledException when unexpected error occurs during remote API call")
    void next5DaysForecast_throws_UnhandledException_when_unexpectedErrorOccursDuringRemoteApiCall() {
        // Arrange
        double lat = 1, lon = 1;
        String apiKey = appConfig.getApiKey(), host = appConfig.getHost(), dailyForecastPath = appConfig.getDailyForecastPath(), units = appConfig.getUnits(), dailyResultCount = appConfig.getDailyResultCount();
        String uri = StringUtils.join(host, dailyForecastPath, "?lat=",lat, "&lon=",lon, "&units=",units, "&cnt=",Integer.valueOf(dailyResultCount), "&appid=",apiKey);
        Exception expectedException = new NullPointerException("exception");

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenThrow(expectedException);

        // Act
        UnhandledException actualException = assertThrows(UnhandledException.class, () -> forecastRemoteApi.next5DaysForecast(lat, lon));

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();
        assertEquals(expectedException, actualException.getCause());
        assertEquals("Unhandled Exception Occurred", actualException.getMessage());
    }

    @Test
    @DisplayName("'next5DaysForecast' throws AppJsonProcessingException when remote API response body is not in json format")
    void next5DaysForecast_throws_AppJsonProcessingException_when_remoteApiResponseBodyIsNotInJsonFormat() {
        // Arrange
        double lat = 1, lon = 1;
        String apiKey = appConfig.getApiKey(), host = appConfig.getHost(), dailyForecastPath = appConfig.getDailyForecastPath(), units = appConfig.getUnits(), dailyResultCount = appConfig.getDailyResultCount();
        String uri = StringUtils.join(host, dailyForecastPath, "?lat=",lat, "&lon=",lon, "&units=",units, "&cnt=",Integer.valueOf(dailyResultCount), "&appid=",apiKey);

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenReturn(ResponseEntity.ok("Invalid Response Body"));

        // Act
        AppJsonProcessingException actualException = assertThrows(AppJsonProcessingException.class, () -> forecastRemoteApi.next5DaysForecast(lat, lon));

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();
        assertInstanceOf(JsonProcessingException.class, actualException.getCause());
        assertEquals("Calling next5DaysForecast api Failed. cause: Invalid json format", actualException.getMessage());
    }

}
