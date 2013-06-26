package ar.edu.itba.it.paw.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.web.command.forms.SearchForm;

@Controller
public class WelcomeController {

	@RequestMapping("public/welcome/index")
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchForm", new SearchForm());
		mav.setViewName("/general/welcome");
		return mav;
	}
	
}
