package com.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.frontend.dto.Book;
import com.frontend.dto.LoginRequest;
import com.frontend.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RestTemplate restTemplate;


	@Override
	public User authenticate(LoginRequest credential) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(credential, headers);

	    try {
	        ResponseEntity<User> response = restTemplate.exchange(
	                "http://localhost:8180/users/login",
	                HttpMethod.POST,
	                requestEntity,
	                new ParameterizedTypeReference<User>() {});

	        if(response.getStatusCode() == HttpStatus.OK) {
	            return response.getBody();
	        } else {
	            System.out.println("Invalid credentials");
	            return null;
	        }
	    } catch (HttpClientErrorException ex) {
	        if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	            System.out.println("Invalid credentials");
	            return null;
	        } else {
	            throw ex;
	        }
	    }
	}

}
