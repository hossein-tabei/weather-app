package demo.application.backend.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import demo.application.backend.LocationTestHelper;
import demo.application.backend.config.AppConfig;
import demo.application.backend.infra.model.ApiLocation;
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

@SpringBootTest(classes = {LocationRemoteApi.class, AppConfig.class, WebClient.class},
        properties = {"remoteConfig.apiKey:apiKey", "remoteConfig.host:http://example.com", "remoteConfig.geo.path.searchGeo:/search/path"}
)
public class LocationRemoteApiTest {

    @Autowired private ILocationRemoteApi locationRemoteApi;
    @Autowired private AppConfig appConfig;
    @MockBean private WebClient client;
    @Mock private WebClient.RequestHeadersUriSpec uriSpecMock;
    @Mock private WebClient.RequestHeadersSpec specMock;
    @Mock private WebClient.ResponseSpec responseSpecMock;
    @Mock private Mono<ResponseEntity<String>> monoResult;

    private static final Gson gson = new Gson();

    @Test
    @DisplayName("'searchLocation' returns a list of locations when all params are valid")
    void searchLocation_returns_aListOfLocations_when_allParamsAreValid() {
        // Arrange
        String searchTerm = "searchTerm", apiKey = appConfig.getApiKey(), host = appConfig.getHost(), searchPath = appConfig.getLocationSearchPath();

        // location 1
        String locationName1 = "locationName1", country1 = "country1", state1 = "state1";
        Double lat1 = 1d, lon1 = 1d;

        // location 2
        String locationName2 = "locationName2", country2 = "country2", state2 = "state2";
        Double lat2 = 2d, lon2 = 2d;

        String uri = StringUtils.join(host, searchPath, "?q=",searchTerm, "&limit=",5, "&appid=",apiKey);
        ApiLocation apiLocation1 = LocationTestHelper.getApiLocation(locationName1, null, lat1, lon1, country1, state1);
        ApiLocation apiLocation2 = LocationTestHelper.getApiLocation(locationName2, null, lat2, lon2, country2, state2);

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenReturn(ResponseEntity.ok(gson.toJson(List.of(apiLocation1, apiLocation2))));

        // Act
        List<ApiLocation> actualResult = locationRemoteApi.searchLocation(searchTerm);

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();

        List<ApiLocation> expectedResult = List.of(apiLocation1, apiLocation2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("'searchLocation' throws WebClientResponseException when remote API returns error")
    void searchLocation_throws_WebClientResponseException_when_remoteApiReturnsError() {
        // Arrange
        String searchTerm = "searchTerm", apiKey = appConfig.getApiKey(), host = appConfig.getHost(), searchPath = appConfig.getLocationSearchPath();
        String uri = StringUtils.join(host, searchPath, "?q=",searchTerm, "&limit=",5, "&appid=",apiKey);
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
                () -> locationRemoteApi.searchLocation(searchTerm));

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
    @DisplayName("'searchLocation' throws UnhandledException when unexpected error occurs during remote API call")
    void searchLocation_throws_UnhandledException_when_unexpectedErrorOccursDuringRemoteApiCall() {
        // Arrange
        String searchTerm = "searchTerm", apiKey = appConfig.getApiKey(), host = appConfig.getHost(), searchPath = appConfig.getLocationSearchPath();
        String uri = StringUtils.join(host, searchPath, "?q=",searchTerm, "&limit=",5, "&appid=",apiKey);
        Exception expectedException = new NullPointerException("exception");

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenThrow(expectedException);

        // Act
        UnhandledException actualException = assertThrows(UnhandledException.class, () -> locationRemoteApi.searchLocation(searchTerm));

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
    @DisplayName("'searchLocation' throws AppJsonProcessingException when remote API response body is not in json format")
    void searchLocation_throws_AppJsonProcessingException_when_remoteApiResponseBodyIsNotInJsonFormat() {
        // Arrange
        String searchTerm = "searchTerm", apiKey = appConfig.getApiKey(), host = appConfig.getHost(), searchPath = appConfig.getLocationSearchPath();
        String uri = StringUtils.join(host, searchPath, "?q=",searchTerm, "&limit=",5, "&appid=",apiKey);

        when(client.get()).thenReturn(uriSpecMock);
        when(uriSpecMock.uri(anyString())).thenReturn(specMock);
        when(specMock.accept(any(MediaType.class))).thenReturn(specMock);
        when(specMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toEntity(any(Class.class))).thenReturn(monoResult);
        when(monoResult.block()).thenReturn(ResponseEntity.ok("Invalid Response Body"));

        // Act
        AppJsonProcessingException actualException = assertThrows(AppJsonProcessingException.class, () -> locationRemoteApi.searchLocation(searchTerm));

        // Assert
        verify(client).get();
        verify(uriSpecMock).uri(uri);
        verify(specMock).accept(MediaType.APPLICATION_JSON);
        verify(specMock).retrieve();
        verify(responseSpecMock).toEntity(String.class);
        verify(monoResult).block();
        assertInstanceOf(JsonProcessingException.class, actualException.getCause());
        assertEquals("Calling searchLocation api Failed. cause: Invalid json format", actualException.getMessage());
    }

}
