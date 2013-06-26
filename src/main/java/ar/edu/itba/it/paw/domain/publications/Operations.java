package ar.edu.itba.it.paw.domain.publications;

public enum Operations {

	SALE("Venta"), RENT("Alquiler");

	private String type;

	private Operations(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
