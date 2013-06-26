package ar.edu.itba.it.paw.web.command.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.forms.LoginForm;

@Component
public class LoginFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	public void validate(Object arg0, Errors errors) {
		LoginForm form = (LoginForm) arg0;

		String username = form.getUsername();
		String password = form.getPassword();
		if (username.isEmpty()) {
			errors.rejectValue("username", "empty");
		}

		if (password.isEmpty()) {
			errors.rejectValue("password", "empty");
		}
	}

}
