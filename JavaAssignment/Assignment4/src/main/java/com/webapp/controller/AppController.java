package com.webapp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.model.Tshirt;
import com.webapp.model.User;
import com.webapp.service.CsvService;
import com.webapp.service.TshirtService;
import com.webapp.service.UserService;

@Controller
public class AppController {

	@Autowired
	UserService userservice;

	@Autowired
	TshirtService tshirtservice;

	@Autowired
	private CsvService csvService;

	@RequestMapping("/welcome")
	public String showWelcomePage() {
		return "welcome";
	}

	@RequestMapping("/search")
	public ModelAndView searchQuery(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		if (session.getAttribute("loggedIn") == null || !(Boolean) session.getAttribute("loggedIn")) {

			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/"); // redirect to home page
			return mv;
		}

		String colour = request.getParameter("colour");
		String size = request.getParameter("size");
		String gender = request.getParameter("gender");
		String sortBy = request.getParameter("sortBy");

		// Check if any field is missing
		if (colour == null || size == null || gender == null || sortBy == null) {
			ModelAndView errorMv = new ModelAndView();
			errorMv.setViewName("redirect:/");
			errorMv.addObject("errorMessage", "All fields are required");
			return errorMv;
		}

		List<Tshirt> tshirts = tshirtservice.searchTshirts(colour, size, gender, sortBy);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("search");
		mv.addObject("result", tshirts);
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView saveUser(HttpServletRequest request, HttpServletResponse reponse) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("display");
		return mv;
	}

	@RequestMapping("/login")
	public String showLoginForm(HttpSession session) {
		if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
			return "redirect:/";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {
		User user = userservice.authenticateUser(username, password);
		 
		ModelAndView mv = new ModelAndView();
		if (user != null) {
			session.setAttribute("user", user);
			session.setAttribute("loggedIn", true);
			return "redirect:/";
		} else {
			model.addAttribute("errorMessage", "Invalid username or password");

			return "login";
		}
		// return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false); // get current session without creating a new one
		if (session != null) {
			session.invalidate(); // invalidate session if it exists
		}
		mv.setViewName("redirect:/"); // redirect to home page
		return mv;
	}

	@PostConstruct
	public void init() {
		System.out.println("--------- Starting csv thread-------");
		csvService.startCsvPollingThread();
	}
}
