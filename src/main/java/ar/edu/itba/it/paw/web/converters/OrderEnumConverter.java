package ar.edu.itba.it.paw.web.converters;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.publications.OrderEnum;

@Component
public class OrderEnumConverter implements Converter<OrderEnum, String> {

	private Map<OrderEnum, String> translator;

	public OrderEnumConverter() {
		this.translator = new HashMap<OrderEnum, String>();
		translator.put(OrderEnum.ASC, "Ascendente");
		translator.put(OrderEnum.DES, "Descendente");

	}

	public String convert(OrderEnum order) {
		return translator.get(order);
	}

}
