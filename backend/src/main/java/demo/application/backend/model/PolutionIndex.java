package demo.application.backend.model;

public class PolutionIndex {

	private String chemicalName;	// NO2
	private float amount;			// 19
	
	public PolutionIndex(String chemicalName, float amount) {
		super();
		this.chemicalName = chemicalName;
		this.amount = amount;
	}

	public String getChemicalName() {
		return chemicalName;
	}

	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PolutionIndex [chemicalName=" + chemicalName + ", amount=" + amount + "]";
	}
	
}
