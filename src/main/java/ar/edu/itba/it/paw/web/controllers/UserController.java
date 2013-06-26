package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.photos.Photo;
import ar.edu.itba.it.paw.domain.users.DuplicatedUserException;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepo;
import ar.edu.itba.it.paw.domain.users.UserType;
import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;
import ar.edu.itba.it.paw.web.command.forms.AgencyRegisterForm;
import ar.edu.itba.it.paw.web.command.forms.LoginForm;
import ar.edu.itba.it.paw.web.command.forms.RegisterForm;
import ar.edu.itba.it.paw.web.command.forms.SearchForm;
import ar.edu.itba.it.paw.web.command.forms.UserRegisterForm;
import ar.edu.itba.it.paw.web.command.validators.AgencyRegisterFormValidator;
import ar.edu.itba.it.paw.web.command.validators.LoginFormValidator;
import ar.edu.itba.it.paw.web.command.validators.UserRegisterFormValidator;

@Controller
public class UserController {

	private LoginFormValidator loginFormValidator;
	private UserRegisterFormValidator userRegistrationValidator;
	private AgencyRegisterFormValidator agencyRegistrationValidator;
	private UserRepo userRepo;

	@Autowired
	public UserController(LoginFormValidator loginFormValidator,
			UserRegisterFormValidator userRegisterFormValidator,
			AgencyRegisterFormValidator agencyRegistrationValidator,
			UserRepo userRepo) {
		this.loginFormValidator = loginFormValidator;
		this.userRegistrationValidator = userRegisterFormValidator;
		this.agencyRegistrationValidator = agencyRegistrationValidator;
		this.userRepo = userRepo;
	}

	@RequestMapping(value = "public/user/register", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		UserManager manager = (UserManager) req.getAttribute("sessionManager");
		if (manager.existsUser()) {
			mav.setViewName("redirect:../../public/welcome/index");
		} else {
			mav.addObject("registerForm", new RegisterForm());
			mav.setViewName("/users/register");
		}
		return mav;
	}

	@RequestMapping(value = "public/user/register", method = RequestMethod.POST)
	public String register(RegisterForm registerForm, Errors errors,
			HttpServletRequest req) {

		UserType userType = registerForm.getUserType();

		if (userType.equals(UserType.AGENCY)) {
			return "redirect:agencyRegister";
		} else {
			return "redirect:userRegister";
		}
	}

	@RequestMapping(value = "public/user/userRegister", method = RequestMethod.GET)
	public ModelAndView showUserRegister(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userRegisterForm", new UserRegisterForm());
		mav.setViewName("/users/userRegister");
		return mav;
	}

	@RequestMapping(value = "public/user/userRegister", method = RequestMethod.POST)
	public String registerUser(UserRegisterForm userRegisterForm,
			Errors errors, HttpServletRequest req) {
		userRegistrationValidator.validate(userRegisterForm, errors);
		if (errors.hasErrors()) {
			return "/users/userRegister";
		}
		if (!register(userRegisterForm.build(), errors))
			return "/users/userRegister";

		User user = userRepo.get(userRegisterForm.getUsername());
		logIn(user, userRegisterForm.getPassword(), req);
		return "redirect:../welcome/index";
	}

	@RequestMapping(value = "public/user/agencyRegister", method = RequestMethod.GET)
	public ModelAndView showAgencyRegister(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("agencyRegisterForm", new AgencyRegisterForm());
		mav.setViewName("/users/agencyRegister");
		return mav;
	}

	@RequestMapping(value = "public/user/agencyRegister", method = RequestMethod.POST)
	public String registerAgency(AgencyRegisterForm agencyRegisterForm,
			Errors errors, HttpServletRequest req) {
		agencyRegistrationValidator.validate(agencyRegisterForm, errors);
		if (errors.hasErrors()) {
			return "/users/agencyRegister";
		}

		if (!register(agencyRegisterForm.build(), errors))
			return "/users/agencyRegister";

		User user = userRepo.get(agencyRegisterForm.getUsername());
		logIn(user, agencyRegisterForm.getPassword(), req);
		return "redirect:../welcome/index";
	}

	private boolean register(User user, Errors errors) {
		try {
			userRepo.add(user);
		} catch (DuplicatedUserException e) {
			errors.rejectValue("username", "existsUser");
			return false;
		}
		return true;
	}

	private void logIn(User user, String password, HttpServletRequest req) {
		if (user != null) {
			user.logIn(password);
		}

		UserManager manager = (UserManager) req.getAttribute("sessionManager");
		manager.setUser(user);
	}

	@RequestMapping(value = "public/user/viewAgencies", method = RequestMethod.GET)
	public ModelAndView viewAgencies() {
		ModelAndView mav = new ModelAndView();
		List<User> agenciesList = userRepo.getAll(UserType.AGENCY);
		mav.addObject("agenciesList", agenciesList);
		mav.addObject("searchForm", new SearchForm());
		mav.setViewName("/users/viewAgencies");
		return mav;
	}

	@RequestMapping(value = "public/user/login", method = RequestMethod.POST)
	public ModelAndView login(LoginForm form, Errors errors,
			HttpServletRequest req, HttpServletResponse resp) {
		loginFormValidator.validate(form, errors);
		UserManager manager = new SessionUserManager(req, resp, userRepo);
		ModelAndView mav = new ModelAndView();
		if (errors.hasErrors()) {
			mav.setViewName("redirect:../../public/user/error");
			return mav;
		}
		User user = userRepo.get(form.getUsername());
		if (user == null || !user.logIn(form.getPassword())) {
			mav.setViewName("redirect:../../public/user/error");
			return mav;
		}
		manager.setUser(user);
		if (form.isRememberUsername()) {
			manager.setRememberMe();
		} else {
			manager.forgetUsername();
		}

		if (form.isRememberLogin()) {
			manager.setKeepMeLogged();
		}

		mav.addObject("searchForm", new SearchForm());
		mav.addObject("sessionManager", manager);
		mav.setViewName("/general/welcome");
		return mav;

	}

	@RequestMapping("private/user/logout")
	public String logout(HttpServletRequest req) {
		UserManager manager = (UserManager) req.getAttribute("sessionManager");
		manager.logout();
		return "redirect:../../public/welcome/index";
	}

	@RequestMapping("public/user/viewPhoto")
	public void viewPhoto(@RequestParam("id") Photo photo,
			HttpServletResponse resp) throws IOException {

		ControllerUtils.RenderPhoto(resp, photo);
	}

	
	@RequestMapping("public/user/error")
	public ModelAndView logInError() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/users/logInError");
		return mav;
	}

}
