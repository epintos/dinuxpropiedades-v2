package ar.edu.itba.it.paw.web.validators.interfaces;

import org.springframework.validation.Errors;

/**
 * Provides an interface to validate different things.
 * 
 */
public interface ValidatorInterface {

	/**
	 * Validates an email format.
	 * 
	 * @param email
	 * @return true if the email format is correct. false if it is not correct.
	 */
	public boolean validateEmail(String email);

	/**
	 * Validates if the length of a data is less than n
	 * @param tovalidate data
	 * @param n max length
	 * @return
	 */
	public boolean validateLenght(String tovalidate, int n);

	/**
	 * Validate if a number is between floor and roof
	 * @param tocheck
	 * @param floor
	 * @param roof
	 * @return
	 */
	public boolean between(int tocheck, int floor, int roof);

	/**
	 * Validates if an integer is positive and not null
	 * @param integer
	 * @param field
	 * @param errors
	 */
	public void validatePositiveInteger(Integer integer, String field,
			Errors errors);

}
