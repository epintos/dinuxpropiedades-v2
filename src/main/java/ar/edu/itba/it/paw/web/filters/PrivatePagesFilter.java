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
public class PrivatePagesFilter extends OncePerRequestFilter {
	
	
	private UserRepo userRepo;

	@Autowired
	public PrivatePagesFilter(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
    
    protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
	HttpServletRequest req = ((HttpServletRequest) request);
	HttpServletResponse resp = ((HttpServletResponse) response);
	UserManager manager = new SessionUserManager(req, resp, userRepo);
	
	if(!manager.existsUser()) {
	    resp.sendRedirect("../../public/welcome/index");
	} else {
	    filterChain.doFilter(request, response);
	}
        
    }

}
