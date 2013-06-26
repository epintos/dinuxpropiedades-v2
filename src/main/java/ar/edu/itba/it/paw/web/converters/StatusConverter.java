package ar.edu.itba.it.paw.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.publications.Status;

@Component
public class StatusConverter implements Converter<Status, String> {

	public StatusConverter() {

	}

	public String convert(Status status) {
		return status.getStatus();
	}

}
