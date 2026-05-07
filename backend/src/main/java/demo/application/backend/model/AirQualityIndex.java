package demo.application.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AirQualityIndex {

	private int numericlevel;		// 28
	private String verbalLevel;		// Good
	private String description;		// Air quality is good. A perfect day for a walk!
	private String publicationInfo;	// Tehran Published at 07:30
	private List<PolutionIndex> polutionIndexes;
}
