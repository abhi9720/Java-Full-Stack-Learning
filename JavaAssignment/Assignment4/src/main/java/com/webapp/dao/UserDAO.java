package com.webapp.dao;

import com.webapp.model.User;

public interface UserDAO {
	
	public User authenticateUser(String username, String password);

}
