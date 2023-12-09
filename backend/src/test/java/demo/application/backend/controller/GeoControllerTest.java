package demo.application.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.application.backend.model.Location;
import demo.application.backend.repository.GeoRepository;

@SpringBootTest
@AutoConfigureMockMvc
class GeoControllerTest {

	@Autowired private MockMvc mockMvc;
	@Autowired private GeoController controller;
	@MockBean private GeoRepository repository;
	@Autowired private ObjectMapper objectMapper;
	
	@Test
	void assertThatGeoControllerIsNotNull() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void assertThatSearch_Oakland_ReturnResultList() throws Exception {
		// Arrange
		Location mockedLocation = new Location(156, 1236, "Oakland", "California", "US");
		List<Location> mockedLocations = new ArrayList<>();
		mockedLocations.add(mockedLocation);
		
		Mockito.doReturn(mockedLocations).when(repository).searchGeo("Oakland");
		String expectedResult = objectMapper.writeValueAsString(mockedLocations);
		System.out.println("expectedResult:"+expectedResult);
		
		
		// Act
		// Assert
		this.mockMvc.perform(get("/api/geo/search")
				.param("searchClause", "Oakland"))
		.andDo(print())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResult))
			;
	}
}
