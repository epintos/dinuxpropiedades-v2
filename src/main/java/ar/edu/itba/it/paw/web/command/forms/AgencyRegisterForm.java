package ar.edu.itba.it.paw.web.command.forms;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.itba.it.paw.domain.photos.Photo;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserType;

public class AgencyRegisterForm {

	private String name;
	private String email;
	private String phone;
	private String username;
	private String password;
	private String passwordConfirm;
	private UserType userType = UserType.AGENCY;
	private CommonsMultipartFile file;

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public User build() {
		User user = new User(getName(), null, getEmail(), getUsername(),
				getPassword(), getPhone(), getUserType());
		if (file.getBytes().length != 0) {
			Photo photo = new Photo(file.getBytes(), user);
			user.setPhoto(photo);
		}
		return user;
	}
}
