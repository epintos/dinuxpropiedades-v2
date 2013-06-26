package ar.edu.itba.it.paw.domain.publications;

public enum PropertyType {

	HOUSE("Casa"), APARTMENT("Departamento");

	private String type;

	private PropertyType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
