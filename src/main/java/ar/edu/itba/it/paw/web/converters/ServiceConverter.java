package ar.edu.itba.it.paw.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.publications.Services;

@Component
public class ServiceConverter implements Converter<Services, String> {

	public ServiceConverter() {
	}

	public String convert(Services service) {
		return service.getName();
	}

}
