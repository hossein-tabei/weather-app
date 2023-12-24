package demo.application.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import demo.application.backend.model.AirQualityIndex;
import demo.application.backend.model.CurrentStatus;
import demo.application.backend.model.DayStatus;
import demo.application.backend.model.HourStatus;
import demo.application.backend.model.Location;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

	@LocalServerPort private int port;
	@Autowired private TestRestTemplate restTemplate;
	
	@Test
	void assertThat_Search_ResultIsNotNullAndStatusIsOK() throws Exception {
		// Arrange
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:" + port + "/api/geo/search")
		        .queryParam("searchClause", "Oakland");
		
		// Act
		ResponseEntity<Location[]> response = restTemplate
				.getForEntity(builder.build().toUriString(), Location[].class);
		
		// Assert
		System.out.println("getBody,Location:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
		
		System.out.println("getBody:"+Arrays.asList(response.getBody()).toString());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void assertThat_CurrentStatus_ResultIsNotNullAndStatusIsOK() throws Exception {
		// Arrange
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:" + port + "/api/forecast/now")
		        .queryParam("lat", "156")
		        .queryParam("lon", "1236");
		
		// Act
		ResponseEntity<CurrentStatus> response = restTemplate
				.getForEntity(builder.build().toUriString(), CurrentStatus.class);
		
		// Assert
		System.out.println("getBody:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
		
		System.out.println("getBody:"+response.getBody().toString());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void assertThat_Next5DaysForecast_ResultIsNotNullAndStatusIsOK() throws Exception {
		// Arrange
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:" + port + "/api/forecast/next5days")
				.queryParam("lat", "156")
		        .queryParam("lon", "1236");
		
		// Act
		ResponseEntity<DayStatus[]> response = restTemplate
				.getForEntity(builder.build().toUriString(), DayStatus[].class);
		
		// Assert
		System.out.println("getBody,DayStatus:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
		
		System.out.println("getBody:"+Arrays.asList(response.getBody()).toString());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void assertThat_Next24HoursForecast_ResultIsNotNullAndStatusIsOK() throws Exception {
		// Arrange
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:" + port + "/api/forecast/next24hours")
				.queryParam("lat", "156")
		        .queryParam("lon", "1236");
		
		// Act
		ResponseEntity<HourStatus[]> response = restTemplate
				.getForEntity(builder.build().toUriString(), HourStatus[].class);
		
		// Assert
		System.out.println("getBody,HourStatus:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
		
		System.out.println("getBody:"+Arrays.asList(response.getBody()).toString());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void assertThat_AirQualityIndex_ResultIsNotNullAndStatusIsOK() throws Exception {
		// Arrange
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:" + port + "/api/forecast/aqi")
		        .queryParam("lat", "156")
		        .queryParam("lon", "1236");
		
		// Act
		ResponseEntity<AirQualityIndex> response = restTemplate
				.getForEntity(builder.build().toUriString(), AirQualityIndex.class);
		
		// Assert
		System.out.println("getBody:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
		
		System.out.println("getBody:"+response.getBody().toString());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
