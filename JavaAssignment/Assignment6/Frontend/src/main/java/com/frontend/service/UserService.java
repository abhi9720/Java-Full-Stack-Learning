package com.frontend.service;

import com.frontend.dto.LoginRequest;
import com.frontend.dto.User;

public interface UserService {

	User authenticate(LoginRequest credential);

	
}
