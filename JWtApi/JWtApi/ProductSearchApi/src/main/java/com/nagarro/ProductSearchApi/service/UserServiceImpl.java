package com.nagarro.ProductSearchApi.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.exception.DuplicateEmailException;
import com.nagarro.ProductSearchApi.exception.InvalidCredentialsException;
import com.nagarro.ProductSearchApi.exception.UserNotFoundException;
import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.repository.UserRepository;
import com.nagarro.ProductSearchApi.security.JwtTokenUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public String loginUser(String email, String password) {
		try {
			// Authenticate the user
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String token = jwtTokenUtil.generateToken(userDetails);

			return token;
		} catch (AuthenticationException e) {
			throw new InvalidCredentialsException("Invalid email or password");
		}
	}

	@Override
	public String registerUser(User user) {
	    // Set the user's password after encoding it
	    user.setPassword(passwordEncoder.encode(user.getPassword()));

	    try {
	        // Check if the email already exists
	       User duplicateUser = userRepository.findByEmail(user.getEmail());
	       if  (duplicateUser!=null) {
	            throw new DuplicateEmailException("Email already exists");
	        }

	        // Save the user to the database
	        userRepository.save(user);

	        // Generate JWT token for the registered user with roles
	        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
	                user.getEmail(), user.getPassword(), getAuthorities(user.getRoles())
	        );
	        String token = jwtTokenUtil.generateToken(userDetails);

	        return token;
	    } catch (DataIntegrityViolationException e) {
	        // Handle other data integrity violations if needed
	        throw new RuntimeException("Registration failed due to a data integrity violation", e);
	    }
	}

	
	private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
	    return Arrays.stream(roles.split(","))
	            .map(SimpleGrantedAuthority::new)
	            .collect(Collectors.toList());
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("User not found with email: " + email);
		}
		return user;
	}

}
