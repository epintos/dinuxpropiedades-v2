package ar.edu.itba.it.paw.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepo;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@Component
public class KeepMeLoggedFilter extends OncePerRequestFilter {

	private UserRepo um;

	@Autowired
	public KeepMeLoggedFilter(UserRepo um) {
		this.um = um;
	}

	public void destroy() {

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = ((HttpServletRequest) request);
		HttpServletResponse resp = ((HttpServletResponse) response);
		UserManager manager = new SessionUserManager(req, resp, um);
		String[] userAndPass = null;
		String user;
		String pass;
		

		if (!manager.existsUser()) {
			Cookie[] cookies = req.getCookies();
			Cookie logInfo = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("KeepMeLogged")) {
						logInfo = c;
					}
				}

				if (logInfo != null) {

					userAndPass = logInfo.getValue().split("/");
					user = userAndPass[0];
					pass = userAndPass[1];

					User u = um.get(user);
					if (u.logIn(pass)) {
						manager.setUser(u);
					} else {
						request.setAttribute("errorType", 500);
						request.getRequestDispatcher("/WEB-INF/jsp/general/error.jsp")
								.forward(request, response);
						return;
					}
				}

			}
		}

		filterChain.doFilter(request, response);
	}

}
