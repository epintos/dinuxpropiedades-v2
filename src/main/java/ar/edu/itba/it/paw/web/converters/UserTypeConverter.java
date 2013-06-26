package ar.edu.itba.it.paw.web.converters;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.users.UserType;

@Component
public class UserTypeConverter implements Converter<UserType, String> {

	private Map<UserType, String> translator;

	public UserTypeConverter() {
		this.translator = new HashMap<UserType, String>();
		translator.put(UserType.AGENCY, "Inmobiliaria");
		translator.put(UserType.USER, "Usuario");

	}

	public String convert(UserType userType) {
		return translator.get(userType);
	}

}
