package demo.application.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {

	private Double lat;
	private Double lon;
	private String city;
	private String state;
	private String country;
}
