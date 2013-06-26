package ar.edu.itba.it.paw.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.publications.RoomsEnum;

@Component
public class TestConverter implements Converter<RoomsEnum, String> {

	
	public String convert(RoomsEnum arg0) {
		return arg0.getDescription();
	};
}
