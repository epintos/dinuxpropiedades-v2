package ar.edu.itba.it.paw.web.command.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.forms.SearchForm;

@Component
public class SearchFormValidator implements Validator, ValidatorDefines {

	public boolean supports(Class<?> clazz) {
		return SearchForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		SearchForm form = (SearchForm) target;
		this.validatePrice(form.getPriceFrom(), errors, "priceFrom");
		this.validatePrice(form.getPriceTo(), errors, "priceTo");
	}

	private void validatePrice(Integer price, Errors errors, String field) {
		if (price != null && price < 0)
			errors.rejectValue(field, NEGATIVE_NUMBER);
	}

}
