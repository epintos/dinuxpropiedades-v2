package ar.edu.itba.it.paw.domain.publications;

public enum Status {

	AVAILABLE("Disponible"), RESERVED("Reservada"), FINISHED("Finalizada");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
