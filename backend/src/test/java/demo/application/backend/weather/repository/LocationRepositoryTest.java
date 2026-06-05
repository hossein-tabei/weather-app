package demo.application.backend.weather.repository;

import demo.application.backend.LocationTestHelper;
import demo.application.backend.infra.ILocationRemoteApi;
import demo.application.backend.infra.model.ApiLocation;
import demo.application.backend.weather.LocationMapperImpl;
import demo.application.backend.weather.model.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LocationRepositoryImpl.class, LocationMapperImpl.class})
public class LocationRepositoryTest {

    @Autowired private LocationRepository locationRepository;
    @MockBean private ILocationRemoteApi iLocationRemoteApi;

    // -----------------------------------------------------------------------------------------------------------------
    // searchLocation
    @Test
    @DisplayName("'searchLocation' returns location list with given search term")
    public void searchLocation_returnsLocationList_WithGivenSearchTerm() {
        // Arrange
        String searchClause = "searchClause";

        Double lat1 = 1d, lon1 = 1d;
        String city1 = "city1", state1 = "state1", country1 = "country1";

        Double lat2 = 2d, lon2 = 2d;
        String city2 = "city2", state2 = "state2", country2 = "country2";

        Location location1 = LocationTestHelper.getLocation(lat1, lon1, city1, state1, country1);
        Location location2 = LocationTestHelper.getLocation(lat2, lon2, city2, state2, country2);
        ApiLocation apiLocation1 = LocationTestHelper.getApiLocation(city1, null, lat1, lon1, country1, state1);
        ApiLocation apiLocation2 = LocationTestHelper.getApiLocation(city2, null, lat2, lon2, country2, state2);
        when(iLocationRemoteApi.searchLocation(anyString())).thenReturn(List.of(apiLocation1, apiLocation2));

        // Act
        List<Location> actualResult = locationRepository.searchLocation(searchClause);

        // Assert
        verify(iLocationRemoteApi).searchLocation(searchClause);
        List<Location> expectedResult = List.of(location1, location2);
        assertEquals(expectedResult, actualResult);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // findLocation
    @Test
    @DisplayName("'findLocation' returns location info with given lat and lon")
    public void findLocation_returnsLocationInfo_WithGivenLatLon() {
        // Arrange
        double lat = 1d, lon = 2d;
        String city = "city", state = "state", country = "country";
        Location expectedResult = LocationTestHelper.getLocation(lat, lon, city, state, country);
        ApiLocation apiLocation = LocationTestHelper.getApiLocation(city, null, lat, lon, country, state);
        when(iLocationRemoteApi.findLocation(anyDouble(), anyDouble())).thenReturn(Optional.of(apiLocation));

        // Act
        Location actualResult = locationRepository.findLocation(lat, lon);

        // Assert
        verify(iLocationRemoteApi).findLocation(lat, lon);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("'findLocation' throws NotFoundException when no data is found with given lat and lon")
    public void findLocation_throws_NotFoundException_whenNoDataIsFoundWithGivenLatLon() {
        // Arrange
        double lat = 1d, lon = 2d;
        when(iLocationRemoteApi.findLocation(anyDouble(), anyDouble())).thenReturn(Optional.empty());

        // Act
        NotFoundException exception = assertThrows(NotFoundException.class, () -> locationRepository.findLocation(lat, lon));

        // Assert
        verify(iLocationRemoteApi).findLocation(lat, lon);
        assertEquals(String.format("No location found for lat: %f, lon: %f", lat, lon), exception.getMessage());
    }
}
