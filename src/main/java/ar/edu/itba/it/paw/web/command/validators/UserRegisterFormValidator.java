package ar.edu.itba.it.paw.web.command.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.forms.UserRegisterForm;
import ar.edu.itba.it.paw.web.validators.implementations.ValidatorImpl;
import ar.edu.itba.it.paw.web.validators.interfaces.ValidatorInterface;

@Component
public class UserRegisterFormValidator implements Validator, ValidatorDefines {

	private final static int maxRegisterFieldLenght = 50;
	ValidatorInterface v = new ValidatorImpl();
	
	public boolean supports(Class<?> clazz) {
		return UserRegisterForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserRegisterForm form = (UserRegisterForm) target;
		this.validateFields(form, errors);

		String password = form.getPassword();
		String passwordConfirm = form.getPasswordConfirm();
		if (!password.isEmpty() && !passwordConfirm.isEmpty()
				&& !password.equals(passwordConfirm)) {
			errors.rejectValue("passwordConfirm", PASSWORD_NOT_MATCH);
		}

		String email = form.getEmail();
		if (!email.isEmpty() && !v.validateEmail(email)) {
			errors.rejectValue("email", BAD_FORMAT);
		}

	}

	private void validateFields(UserRegisterForm form, Errors errors) {
		validateField("name", form.getName(), errors);
		validateField("surname", form.getSurname(), errors);
		validateField("email", form.getEmail(), errors);
		validateField("phone", form.getPhone(), errors);
		validateField("username", form.getUsername(), errors);
		validateField("password", form.getPassword(), errors);
		validateField("passwordConfirm", form.getPasswordConfirm(), errors);
	}

	private void validateField(String field, String value, Errors errors) {
		String auxValue = value.replace(" ", "");
		if (auxValue.isEmpty()) {
			errors.rejectValue(field, INFORMATION_MISSING_MSG);
		} else {
			if (!v.validateLenght(value, maxRegisterFieldLenght)) {
				errors.rejectValue(field, VERY_LONG_STRING);
			}
		}
	}
}
