package ar.edu.itba.it.paw.domain.publications;

public enum OrderEnum {

	DES("Des"), ASC("Asc");

	private String order;

	private OrderEnum(String order) {
		this.order = order;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
