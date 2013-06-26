package ar.edu.itba.it.paw.web.command.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.forms.CommentForm;
import ar.edu.itba.it.paw.web.validators.implementations.ValidatorImpl;
import ar.edu.itba.it.paw.web.validators.interfaces.ValidatorInterface;

@Component
public class CommentFormValidator implements Validator, ValidatorDefines {

	private final static int maxCommentLenght = 300;

	public boolean supports(Class<?> clazz) {
		return CommentForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		CommentForm form = (CommentForm) target;
		ValidatorInterface v = new ValidatorImpl();

		if (form.getName().isEmpty()) {
			errors.rejectValue("name", INFORMATION_MISSING_MSG);
		}

		if (form.getEmail().isEmpty()) {
			errors.rejectValue("email", INFORMATION_MISSING_MSG);
		} else if (!form.getEmail().isEmpty()
				&& !v.validateEmail(form.getEmail())) {
			errors.rejectValue("email", BAD_FORMAT);
		}

		if (form.getPhone().isEmpty()) {
			errors.rejectValue("phone", INFORMATION_MISSING_MSG);
		}

		if (!v.validateLenght(form.getComment().toString(), maxCommentLenght))
			errors.rejectValue("comment", VERY_LONG_STRING);

	}
}
