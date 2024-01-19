package com.webapp.service;

import com.webapp.model.User;

public interface UserService {
	
	public User authenticateUser(String username, String password);

}
