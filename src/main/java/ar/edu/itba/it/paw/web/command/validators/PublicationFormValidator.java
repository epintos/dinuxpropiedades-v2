package ar.edu.itba.it.paw.web.command.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.domain.publications.PropertyType;
import ar.edu.itba.it.paw.web.command.forms.PublicationForm;
import ar.edu.itba.it.paw.web.validators.implementations.ValidatorImpl;
import ar.edu.itba.it.paw.web.validators.interfaces.ValidatorInterface;

@Component
public class PublicationFormValidator implements Validator, ValidatorDefines {

	private final static int maxNeighbourhoodLenght = 50;
	private final static int maxStreetLenght = 50;
	private final static int maxApartmentLenght = 5;
	private final static int maxDescriptionLenght = 512;

	private ValidatorInterface v = new ValidatorImpl();

	public boolean supports(Class<?> clazz) {
		return PublicationForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		PublicationForm form = (PublicationForm) target;

		this.validateFields(form, errors);

		if (form.getPropertyType().equals(PropertyType.APARTMENT)) {
			validateField("floor", form.getFloor(), errors);
			validateField("apartment", form.getApartment(), maxApartmentLenght,
					errors);
		}
	}

	private void validateFields(PublicationForm form, Errors errors) {
		validateField("street", form.getStreet(), maxStreetLenght, errors);
		validateField("numbering", form.getNumbering(), errors);
		validateField("neighbourhood", form.getNeighbourhood(),
				maxNeighbourhoodLenght, errors);
		validateField("roomsQty", form.getRoomsQty(), errors);
		validateField("price", form.getPrice(), errors);
		validateField("age", form.getAge(), errors);
		validateField("coveredSurface", form.getCoveredSurface(), errors);
		validateField("uncoveredSurface", form.getUncoveredSurface(), errors);

		if (!v.validateLenght(form.getDescription(), maxDescriptionLenght)) {
			errors.rejectValue("description", VERY_LONG_STRING);
		}

	}

	private void validateField(String field, String value, int length,
			Errors errors) {
		// In case there are all spaces.
		String auxValue = value.replace(" ", "");
		if (auxValue.isEmpty()) {
			errors.rejectValue(field, INFORMATION_MISSING_MSG);
		} else {
			if (!v.validateLenght(value, length)) {
				errors.rejectValue(field, VERY_LONG_STRING);
			}
		}
	}

	private void validateField(String field, Integer value, Errors errors) {
		v.validatePositiveInteger(value, field, errors);
	}

}
