package ar.edu.itba.it.paw.domain.publications;

public enum Services {

	CABLE("Cable"), SWIMMINGPOOL("Pileta de Natación"), SALON("Salón"), TELEPHONE(
			"Teléfono"), PADDLECOURT("Cancha de Paddle"), QUINCHO("Quincho"), TENNISCOURT(
			"Cancha de Tenis"), SECURITY("Seguridad Nocturna"), LAUNDRY(
			"Servicio de Limpieza"), SOLARIUM("Solarium");

	private String name;

	private Services(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Services getService() {
		return this;
	}

}
