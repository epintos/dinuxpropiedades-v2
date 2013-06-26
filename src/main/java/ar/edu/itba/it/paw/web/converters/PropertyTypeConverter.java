package ar.edu.itba.it.paw.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.publications.PropertyType;

@Component
public class PropertyTypeConverter implements Converter<PropertyType, String> {

	public PropertyTypeConverter() {

	}

	public String convert(PropertyType operation) {
		return operation.getType();
	}

}
