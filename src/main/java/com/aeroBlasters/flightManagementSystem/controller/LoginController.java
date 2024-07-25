package com.aeroBlasters.flightManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aeroBlasters.flightManagementSystem.bean.FlightUser;
import com.aeroBlasters.flightManagementSystem.exception.LoginException;
import com.aeroBlasters.flightManagementSystem.service.FlightUserService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class LoginController {

	@Autowired
	private FlightUserService service;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	// Displays the registration page for new users
	@GetMapping("/register")
	public ModelAndView showUserRegisterPage() {
		FlightUser user = new FlightUser();
		ModelAndView mv = new ModelAndView("newUserEntry");
		mv.addObject("userRecord", user);
		return mv;
	}

	// Displays the login page
	@GetMapping("/loginpage")
	public ModelAndView showloginPage() {
		return new ModelAndView("loginPage");
	}

	// Displays the login error page
	@GetMapping("/loginErrorPage")
	public ModelAndView showloginErrorPage() {
		return new ModelAndView("loginErrorPage");
	}

	// Handles user registration and encrypts the password
	@PostMapping("/register")
	public ModelAndView saveUserRegistrationPage(@ModelAttribute("userRecord") FlightUser user) {
		String encodedPassword = bCrypt.encode(user.getPassword()); // Encrypts the password
		FlightUser newUser = new FlightUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(encodedPassword);
		newUser.setType(user.getType());
		service.save(newUser);
		return new ModelAndView("loginPage");
	}

	// Displays the index page based on user type (admin or customer)
	@GetMapping({ "/index", "/" })
	public ModelAndView showIndexPage() {
		try {
			String indexPage = "";
			String userType = service.getType();
			if (userType == null) {
				throw new LoginException("Please Log in to continue...");
			}
			System.out.println("User Type: " + userType);
			if (userType.equalsIgnoreCase("admin")) {
				indexPage = "adminIndex";
			} else if (userType.equalsIgnoreCase("customer")) {
				indexPage = "customerIndex";
			}
			return new ModelAndView(indexPage);
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}

	}

	@ExceptionHandler(value = LoginException.class)
	public ModelAndView handlingLoginException(LoginException exception) {
		String message = "Login Exception: " + exception.getMessage();
		ModelAndView mv = new ModelAndView("loginPage");
		mv.addObject("errorMessage", message);
		return mv;
	}
}