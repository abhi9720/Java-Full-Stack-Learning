package com.frontend.controller;

import javax.servlet.http.Cookie;
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

import com.frontend.dto.LoginRequest;
import com.frontend.dto.User;
import com.frontend.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	
//	HttpSession session in parameter
	
	@RequestMapping("/login")
	public String showLogin() {
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String LoginUser(HttpServletRequest request, HttpServletResponse response,
	                        @RequestParam("email") String email, @RequestParam("password") String password,
	                        Model model) {
	    LoginRequest credential = new LoginRequest(email, password);
	    User user = userService.authenticate(credential);

	    if (user != null) {
	        HttpSession loginsession = request.getSession();
	        loginsession.setMaxInactiveInterval(3600);// 1 Hr
	        loginsession.setAttribute("user", user);
	        loginsession.setAttribute("loggedIn", true);

	        // Set session cookie
	        if (!response.isCommitted()) {
	            Cookie sessionCookie = new Cookie("JSESSIONID", loginsession.getId());
	            sessionCookie.setMaxAge(loginsession.getMaxInactiveInterval());
	            sessionCookie.setSecure(true);
	            sessionCookie.setHttpOnly(true);
	            response.addCookie(sessionCookie);
	        }

	        System.out.println("Session added Successfully");
	        return "redirect:/books";

	    } else {
	        model.addAttribute("error", "Invalid credentials. Please try again.");
	        return "forward:/";
	    }
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
}
