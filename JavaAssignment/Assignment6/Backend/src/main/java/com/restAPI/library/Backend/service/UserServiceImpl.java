package com.restAPI.library.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restAPI.library.Backend.entity.User;
import com.restAPI.library.Backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	@Override
	public boolean isValidUserCredentials(String email, String password) {
		User user = userRepository.findByEmail(email);
	    if (user == null) {
	        return false;
	    }
	    return user.getPassword().equals(password);
	}

	@Override
	public String generateToken(User userByEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
