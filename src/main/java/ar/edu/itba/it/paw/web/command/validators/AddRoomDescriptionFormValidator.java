package ar.edu.itba.it.paw.web.command.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.forms.AddRoomDescriptionForm;
import ar.edu.itba.it.paw.web.command.forms.CommentForm;
import ar.edu.itba.it.paw.web.validators.implementations.ValidatorImpl;
import ar.edu.itba.it.paw.web.validators.interfaces.ValidatorInterface;

@Component
public class AddRoomDescriptionFormValidator implements Validator,
		ValidatorDefines {

	public boolean supports(Class<?> clazz) {
		return CommentForm.class.equals(clazz);
	}

	public void validate(Object arg0, Errors errors) {
		AddRoomDescriptionForm form = (AddRoomDescriptionForm) arg0;
		ValidatorInterface v = new ValidatorImpl();

		v.validatePositiveInteger(form.getLength(), "length", errors);
		v.validatePositiveInteger(form.getWidth(), "width", errors);
	}
}
