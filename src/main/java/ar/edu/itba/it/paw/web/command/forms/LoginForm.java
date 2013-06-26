package ar.edu.itba.it.paw.web.command.forms;

public class LoginForm {

	private String username;
	private String password;
	private boolean rememberUsername;
	private boolean rememberLogin;

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
	
	public boolean isRememberLogin() {
		return rememberLogin;
	}
	
	public boolean isRememberUsername() {
		return rememberUsername;
	}
	
	public void setRememberLogin(boolean rememberLogin) {
		this.rememberLogin = rememberLogin;
	}
	
	public void setRememberUsername(boolean rememberUsername) {
		this.rememberUsername = rememberUsername;
	}
	
	

}
