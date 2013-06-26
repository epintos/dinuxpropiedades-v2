package ar.edu.itba.it.paw.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.publications.Operations;

@Component
public class OperationsConverter implements Converter<Operations, String> {

	public OperationsConverter() {

	}

	public String convert(Operations operation) {
		return operation.getType();
	}

}
