package ar.edu.itba.it.paw.domain.publications;

public enum RoomsEnum {

	LIVINGROOM("Living Room"), BEDROOM("Habitaci—n"), KITCHEN("Cocina"), BATHROOM(
			"Ba–o"), BALCONY("Balc—n"), STUDY("Estudio"), GARDEN("Jard’n");

	private String description;

	private RoomsEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
