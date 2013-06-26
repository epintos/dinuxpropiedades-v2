package ar.edu.itba.it.paw.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepo;

public class SessionUserManager implements UserManager {

	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	private UserRepo userRepo;
	
	private static String key = "USERID";
	private static String keepMeLoggedKey = "KeepMeLogged";
	private static String rememberMeKey = "RememberMePlz";

	public SessionUserManager(HttpServletRequest request,
			HttpServletResponse response, UserRepo userRepo) {
		this.request = request;
		session = request.getSession();
		this.response = response;
		this.userRepo = userRepo;
	}

	public boolean existsUser() {
		return session.getAttribute(key) != null;
	}

	public User getUser() {
		if(existsUser())
			return userRepo.get((Integer) session.getAttribute(key));
		return null;
	}

	public void setUser(User user) {
		session.setAttribute(key, user.getId());

	}

	public void logout() {
		session.invalidate();
		Cookie keepMeLogged = new Cookie(keepMeLoggedKey, " ");
		keepMeLogged.setMaxAge(0);
		keepMeLogged.setPath(request.getContextPath());
		response.addCookie(keepMeLogged);
	}

	public void setKeepMeLogged() {
		User user = getUser();
		Cookie logged = new Cookie(keepMeLoggedKey, user.getUsername() + "/"
				+ user.getPassword());
		logged.setMaxAge(60 * 60 * 24);
		logged.setPath(request.getContextPath());
		response.addCookie(logged);
	}

	public void setRememberMe() {
		User user = getUser();
		Cookie rememberMe = new Cookie(rememberMeKey, user.getUsername());
		rememberMe.setMaxAge(60 * 60 * 24);
		rememberMe.setPath(request.getContextPath());
		response.addCookie(rememberMe);
	}
	
	public void forgetUsername() {
		Cookie rememberMe = new Cookie(rememberMeKey, "");
		rememberMe.setMaxAge(60 * 60 * 24);
		rememberMe.setPath(request.getContextPath());
		response.addCookie(rememberMe);
	}

}
