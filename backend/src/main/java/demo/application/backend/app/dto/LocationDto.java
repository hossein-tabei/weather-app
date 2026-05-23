package demo.application.backend.app.dto;

import lombok.Data;

@Data
public class LocationDto {

	private Double lat;
	private Double lon;
	private String city;
	private String state;
	private String country;
}
