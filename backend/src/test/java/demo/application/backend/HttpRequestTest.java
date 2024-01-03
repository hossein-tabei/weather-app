package demo.application.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {
	private Logger logger = LoggerFactory.getLogger(HttpRequestTest.class);
	
	@LocalServerPort private int port;
	@Autowired private TestRestTemplate restTemplate;
	
	@Test
	void assertThat_Search_ResultIsNotNullAndStatusIsOK() throws Exception {
		// Arrange
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:" + port + "/api/geo/search")
		        .queryParam("searchClause", "Oakland");
		
		// Act
		ResponseEntity<String> response = restTemplate
				.getForEntity(builder.build().toUriString(), String.class);
		
		// Assert
		logger.trace("getBody,Location:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void assertThat_CurrentStatus_ResultIsNotNullAndStatusIsOK() throws Exception {
		// Arrange
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:" + port + "/api/forecast/now")
		        .queryParam("lat", "51.507320404052734")
		        .queryParam("lon", "-0.12764739990234375");
		
		// Act
		ResponseEntity<String> response = restTemplate
				.getForEntity(builder.build().toUriString(), String.class);
		
		// Assert
		logger.trace("getBody,CurrentStatus:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
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
		ResponseEntity<String> response = restTemplate
				.getForEntity(builder.build().toUriString(), String.class);
		
		// Assert
		logger.trace("getBody,DayStatus:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
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
		ResponseEntity<String> response = restTemplate
				.getForEntity(builder.build().toUriString(), String.class);
		
		// Assert
		logger.trace("getBody,HourStatus:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
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
		ResponseEntity<String> response = restTemplate
				.getForEntity(builder.build().toUriString(), String.class);
		
		// Assert
		logger.trace("getBody,AirQualityIndex:"+response.getBody());
		assertThat(response.getBody()).isNotNull();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
