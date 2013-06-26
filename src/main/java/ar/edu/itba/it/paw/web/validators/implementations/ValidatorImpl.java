package ar.edu.itba.it.paw.web.validators.implementations;

import org.springframework.validation.Errors;

import ar.edu.itba.it.paw.web.command.validators.ValidatorDefines;
import ar.edu.itba.it.paw.web.validators.interfaces.ValidatorInterface;

public class ValidatorImpl implements ValidatorInterface, ValidatorDefines {

	public boolean validateEmail(String email) {

		if (email == null)
			return false;
		return email.matches("[a-z0-9.-_]*@[a-z0-9.-_]*");
	}


	public void validatePositiveInteger(Integer integer, String field,
			Errors errors) {
		if(errors.hasFieldErrors(field))
			return;
		if(integer == null){
			errors.rejectValue(field, INFORMATION_MISSING_MSG);
			return;
		}
		if (integer < 0) {
			errors.rejectValue(field, NEGATIVE_NUMBER);

		}
	}

	public boolean validateLenght(String tovalidate, int n) {
		return !(tovalidate.length() > n);
	}

	public boolean between(int tocheck, int floor, int roof) {
		return (tocheck >= floor) && (tocheck <= roof);

	}

}
