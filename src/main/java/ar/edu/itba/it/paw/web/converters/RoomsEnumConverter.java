package ar.edu.itba.it.paw.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.publications.RoomsEnum;

@Component
public class RoomsEnumConverter implements Converter<RoomsEnum, String> {

	public RoomsEnumConverter() {

	}

	public String convert(RoomsEnum room) {
		return room.getDescription();
	}

}
