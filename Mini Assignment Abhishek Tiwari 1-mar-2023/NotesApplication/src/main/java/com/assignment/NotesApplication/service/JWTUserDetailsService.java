package com.assignment.NotesApplication.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.NotesApplication.entity.User;
import com.assignment.NotesApplication.repository.UserRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println("user "+ user);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid User ");
        }

        return new org.springframework.security.core.userdetails.User(
               user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }	
}

