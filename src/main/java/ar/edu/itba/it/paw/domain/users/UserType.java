package ar.edu.itba.it.paw.domain.users;

public enum UserType {

	USER("User"), AGENCY("Agency");

	private String userType;

	private UserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
