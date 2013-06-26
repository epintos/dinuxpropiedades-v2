package ar.edu.itba.it.paw.web.command.forms;

import java.util.Arrays;
import java.util.List;

import ar.edu.itba.it.paw.domain.publications.Operations;
import ar.edu.itba.it.paw.domain.publications.OrderEnum;
import ar.edu.itba.it.paw.domain.publications.PropertyType;
import ar.edu.itba.it.paw.domain.publications.Search;

public class SearchForm {

	private Operations operationType;
	private PropertyType propertyType;
	private Integer priceFrom;
	private Integer priceTo;
	private OrderEnum order;
	private Integer page;

	public SearchForm() {
		page = 1;
	}

	public List<Operations> getAllOperationsType() {
		return Arrays.asList(Operations.values());
	}

	public List<PropertyType> getAllPropertyType() {
		return Arrays.asList(PropertyType.values());
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public void setOperationType(Operations operationType) {
		this.operationType = operationType;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public Operations getOperationType() {
		return operationType;
	}

	public Integer getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(Integer priceFrom) {
		this.priceFrom = priceFrom;
	}

	public Integer getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(Integer priceTo) {
		this.priceTo = priceTo;
	}

	public OrderEnum getOrder() {
		if (order == null) {
			order = OrderEnum.ASC;
			return order;
		} else
			return order;
	}

	public Integer getPage() {
		if (page <= 0)
			page = 1;
		return page;
	}

	public void setPage(Integer page) {
		if (page <= 0)
			this.page = 1;
		this.page = page;
	}

	public void setOrder(OrderEnum order) {
		this.order = order;
	}

	public Search build(Integer from, Integer resultsPerPage) {
		Search search = new Search(getOperationType(), getPropertyType(),
				getPriceFrom(), getPriceTo(), getOrder(), from, resultsPerPage);
		return search;
	}

}
