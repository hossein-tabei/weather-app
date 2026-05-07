package demo.application.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PolutionIndex {

	private String chemicalName;	// NO2
	private float amount;			// 19
}
