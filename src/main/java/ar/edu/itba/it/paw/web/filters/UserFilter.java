package ar.edu.itba.it.paw.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.edu.itba.it.paw.domain.users.UserRepo;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

@Component
public class UserFilter extends OncePerRequestFilter {

	private UserRepo userRepo;

	@Autowired
	public UserFilter(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpServletRequest req = ((HttpServletRequest) request);
		HttpServletResponse resp = ((HttpServletResponse) response);

		UserManager manager = new SessionUserManager(req, resp, userRepo);
		req.setAttribute("sessionManager", manager);

		filterChain.doFilter(request, response);

	}

}
