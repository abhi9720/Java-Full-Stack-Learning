package com.restAPI.library.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restAPI.library.Backend.dto.LoginRequest;
import com.restAPI.library.Backend.entity.User;
import com.restAPI.library.Backend.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("users/login")

	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

		if (userService.isValidUserCredentials(loginRequest.getEmail(), loginRequest.getPassword())) {
//		        String token = userService.generateToken(userService.getUserByEmail(loginRequest.getEmail()));
//		        return ResponseEntity.ok(token);

			User result = userService.getUserByEmail(loginRequest.getEmail());
			result.setPassword(null);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
		}
	}
}
