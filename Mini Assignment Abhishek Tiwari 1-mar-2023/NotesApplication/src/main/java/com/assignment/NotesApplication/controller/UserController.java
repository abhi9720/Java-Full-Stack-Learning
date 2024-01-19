package com.assignment.NotesApplication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.NotesApplication.dto.LoginRequest;
import com.assignment.NotesApplication.entity.User;
import com.assignment.NotesApplication.secuirty.JwtTokenUtil;
import com.assignment.NotesApplication.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/register")
	public ResponseEntity<?> RegisterUser(@RequestBody User user) {
		try {
			User registeredUser = userService.registerUser(user);
			return ResponseEntity.ok(registeredUser);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> LoginUser(@RequestBody LoginRequest loginRequest) {

		System.out.println(loginRequest.getEmail() + " " + loginRequest.getPassword());
		try {
			 authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
	            );

		System.out.println("here ");
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or password");
		}

		System.out.println("Here 2");
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
		String token = jwtTokenUtil.generateToken(userDetails);
		Map response = new HashMap<>();

		response.put("token", token);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
