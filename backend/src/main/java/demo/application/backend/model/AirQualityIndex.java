package demo.application.backend.model;

import java.util.List;

public class AirQualityIndex {

	private int numericlevel;		// 28
	private String verbalLevel;		// Good
	private String description;		// Air quality is good. A perfect day for a walk!
	private String publicationInfo;	// Tehran Published at 07:30
	private List<PolutionIndex> polutionIndexes;
	
	public AirQualityIndex(int numericlevel, String verbalLevel, String description, String publicationInfo,
			List<PolutionIndex> polutionIndexes) {
		super();
		this.numericlevel = numericlevel;
		this.verbalLevel = verbalLevel;
		this.description = description;
		this.publicationInfo = publicationInfo;
		this.polutionIndexes = polutionIndexes;
	}

	public int getNumericlevel() {
		return numericlevel;
	}

	public void setNumericlevel(int numericlevel) {
		this.numericlevel = numericlevel;
	}

	public String getVerbalLevel() {
		return verbalLevel;
	}

	public void setVerbalLevel(String verbalLevel) {
		this.verbalLevel = verbalLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublicationInfo() {
		return publicationInfo;
	}

	public void setPublicationInfo(String publicationInfo) {
		this.publicationInfo = publicationInfo;
	}

	public List<PolutionIndex> getPolutionIndexes() {
		return polutionIndexes;
	}

	public void setPolutionIndexes(List<PolutionIndex> polutionIndexes) {
		this.polutionIndexes = polutionIndexes;
	}

	@Override
	public String toString() {
		return "AirQualityIndex [numericlevel=" + numericlevel + ", verbalLevel=" + verbalLevel + ", description="
				+ description + ", publicationInfo=" + publicationInfo + ", polutionIndexes="
				+ polutionIndexes.toString() + "]";
	}
	
}
