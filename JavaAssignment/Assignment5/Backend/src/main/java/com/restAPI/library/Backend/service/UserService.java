package com.restAPI.library.Backend.service;

import com.restAPI.library.Backend.entity.User;

public interface UserService {

	User getUserByEmail(String email);
    boolean isValidUserCredentials(String email, String password);
	String generateToken(User userByEmail);
}
