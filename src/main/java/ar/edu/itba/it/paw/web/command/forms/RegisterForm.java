package ar.edu.itba.it.paw.web.command.forms;

import java.util.Arrays;
import java.util.List;

import ar.edu.itba.it.paw.domain.users.UserType;

public class RegisterForm {

	private UserType userType;

	public List<UserType> getAllUsersType() {
		return Arrays.asList(UserType.values());
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
