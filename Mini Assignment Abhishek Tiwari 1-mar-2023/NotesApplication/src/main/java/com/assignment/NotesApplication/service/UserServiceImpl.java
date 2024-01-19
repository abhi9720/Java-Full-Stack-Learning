package com.assignment.NotesApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.NotesApplication.entity.User;
import com.assignment.NotesApplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		 
		String encoderPasswod =  passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encoderPasswod);
		
		User savedUser =  userRepository.save(user);
		return savedUser;
	}

}
