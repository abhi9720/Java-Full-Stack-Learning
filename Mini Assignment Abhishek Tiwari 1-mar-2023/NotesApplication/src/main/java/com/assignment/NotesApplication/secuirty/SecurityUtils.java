package com.assignment.NotesApplication.secuirty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.assignment.NotesApplication.entity.User;
import com.assignment.NotesApplication.repository.UserRepository;



@Component
public class SecurityUtils {
	
	@Autowired
	UserRepository userRepository;
	
	
	public  User getCurrentUser() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null) {
	            String email = ((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername();
	            return userRepository.findByEmail(email);
	           
	        }
	        
	        return null;
	}

}
