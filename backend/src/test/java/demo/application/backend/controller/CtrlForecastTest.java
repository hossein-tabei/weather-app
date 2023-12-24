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

import demo.application.backend.model.AirQualityIndex;
import demo.application.backend.model.CurrentStatus;
import demo.application.backend.model.DTOResultWrapper;
import demo.application.backend.model.DayStatus;
import demo.application.backend.model.HourStatus;
import demo.application.backend.model.PolutionIndex;
import demo.application.backend.service.SrvcForecast;

@SpringBootTest
@AutoConfigureMockMvc
class CtrlForecastTest {

	@Autowired private MockMvc mockMvc;
	@Autowired private CtrlForecast controller;
	@MockBean private SrvcForecast service;
	@Autowired private ObjectMapper objectMapper;
	
	@Test
	void assertThatForecastControllerIsNotNull() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void assertThat_CurrentStatus_ReturnExpectedResultWithTheGivenInput() throws Exception {
		// Arrange
		CurrentStatus mockedCurrentStatus = new CurrentStatus("Oakland", "United States", "Clear", 19, 15, 25, 35, 14, 10);
		Mockito.doReturn(mockedCurrentStatus).when(service).currentStatus(156, 1236);
		String expectedResult = objectMapper
				.writeValueAsString(new DTOResultWrapper<CurrentStatus>(1, "Operation Done Successfully", mockedCurrentStatus));
		System.out.println("expectedResult:"+expectedResult);
		
		// Act
		// Assert
		this.mockMvc.perform(get("/api/forecast/now")
				.param("lat", "156")
				.param("lon", "1236"))
		.andDo(print())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResult));
	}
	
	@Test
	void assertThat_Next5DaysForecast_ReturnExpectedResultWithTheGivenInput() throws Exception {
		// Arrange
		List<DayStatus> mockedNext5DayStats = new ArrayList<>();
		mockedNext5DayStats.add(new DayStatus("Today"		, "2023/11/27", "Cloudy", "Clear", 6, 16, 7.4f));
		mockedNext5DayStats.add(new DayStatus("Tomorrow"	, "2023/11/28", "Cloudy", "Clear", 6, 16, 7.4f));
		mockedNext5DayStats.add(new DayStatus("Wednesday"	, "2023/11/29", "Cloudy", "Clear", 6, 16, 7.4f));
		mockedNext5DayStats.add(new DayStatus("Thursday"	, "2023/11/30", "Cloudy", "Clear", 6, 16, 7.4f));
		mockedNext5DayStats.add(new DayStatus("Friday"		, "2023/12/01", "Cloudy", "Clear", 6, 16, 7.4f));
		Mockito.doReturn(mockedNext5DayStats).when(service).next5DaysForecast(156, 1236);
		String expectedResult = objectMapper
				.writeValueAsString(new DTOResultWrapper<List<DayStatus>>(1, "Operation Done Successfully", mockedNext5DayStats));
		System.out.println("expectedResult:"+expectedResult);
		
		// Act
		// Assert
		this.mockMvc.perform(get("/api/forecast/next5days")
				.param("lat", "156")
				.param("lon", "1236"))
		.andDo(print())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResult));
	}
	
	@Test
	void assertThat_Next24HoursForecast_ReturnExpectedResultWithTheGivenInput() throws Exception {
		// Arrange
		List<HourStatus> mockedNext5DayStats = new ArrayList<>();
		mockedNext5DayStats.add(new HourStatus(14, "Cloudy", 7.4f, "Now"));
		mockedNext5DayStats.add(new HourStatus(14, "Cloudy", 7.4f, "23:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Cloudy", 7.4f, "01:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Cloudy", 7.4f, "02:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Cloudy", 7.4f, "03:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Cloudy", 7.4f, "04:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Cloudy", 7.4f, "05:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Cloudy", 7.4f, "06:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "07:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "08:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "09:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "10:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "11:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "12:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "13:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "14:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "15:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "16:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "17:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "18:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "19:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "20:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "21:00"));
		mockedNext5DayStats.add(new HourStatus(14, "Clear", 7.4f, "22:00"));
		Mockito.doReturn(mockedNext5DayStats).when(service).next24HoursForecast(156, 1236);
		String expectedResult = objectMapper
				.writeValueAsString(new DTOResultWrapper<List<HourStatus>>(1, "Operation Done Successfully", mockedNext5DayStats));
		System.out.println("expectedResult:"+expectedResult);
		
		// Act
		// Assert
		this.mockMvc.perform(get("/api/forecast/next24hours")
				.param("lat", "156")
				.param("lon", "1236"))
		.andDo(print())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResult));
	}
	
	@Test
	void assertThat_AirQualityIndex_ReturnExpectedResultWithTheGivenInput() throws Exception {
		// Arrange
		List<PolutionIndex> polutionIndexes = new ArrayList<>();
		polutionIndexes.add(new PolutionIndex("PM2.5", 28.9f));
		polutionIndexes.add(new PolutionIndex("PM10", 16.4f));
		polutionIndexes.add(new PolutionIndex("SO2", 3));
		polutionIndexes.add(new PolutionIndex("NO2", 19));
		polutionIndexes.add(new PolutionIndex("O3", 12.7f));
		polutionIndexes.add(new PolutionIndex("CO", 2.7f));
		AirQualityIndex mockedAirQualityIndex = new AirQualityIndex(28, "Good",
											"Air quality is good. A perfect day for a walk!",
											"Oakland Published at 07:30", polutionIndexes);
		Mockito.doReturn(mockedAirQualityIndex).when(service).airQualityIndex(156, 1236);
		String expectedResult = objectMapper
				.writeValueAsString(new DTOResultWrapper<AirQualityIndex>(1, "Operation Done Successfully", mockedAirQualityIndex));
		System.out.println("expectedResult:"+expectedResult);
		
		// Act
		// Assert
		this.mockMvc.perform(get("/api/forecast/aqi")
				.param("lat", "156")
				.param("lon", "1236"))
		.andDo(print())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResult));
	}
}
