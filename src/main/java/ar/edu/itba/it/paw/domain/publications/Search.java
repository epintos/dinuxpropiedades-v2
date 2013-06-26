package ar.edu.itba.it.paw.domain.publications;


public class Search {
	private Operations operationType;
	private PropertyType propertyType;
	private Integer priceFrom;
	private Integer priceTo;
	private OrderEnum order;
	private Integer from;
	private Integer resultsPerPage;

	public Search(Operations operationType, PropertyType propertyType,
			Integer priceFrom, Integer priceTo, OrderEnum order, Integer from,
			Integer resultsPerPage) {
		this.operationType = operationType;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.order = order;
		this.from = from;
		this.resultsPerPage = resultsPerPage;
		this.propertyType = propertyType;
	}

	public Operations getOperationType() {
		return operationType;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public Integer getPriceFrom() {
		return priceFrom;
	}

	public Integer getPriceTo() {
		return priceTo;
	}

	public OrderEnum getOrder() {
		return order;
	}

	public Integer getFrom() {
		return from;
	}

	public Integer getResultsPerPage() {
		return resultsPerPage;
	}
}
