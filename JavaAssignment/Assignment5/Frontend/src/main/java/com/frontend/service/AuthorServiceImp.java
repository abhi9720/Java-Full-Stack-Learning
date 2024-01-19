package com.frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.frontend.dto.Author;

@Service
public class AuthorServiceImp implements AuthorService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Author> getAllAuthors() {

		ResponseEntity<List<Author>> response = restTemplate.exchange(
				"http://localhost:8180/authors", 
				HttpMethod.GET,
				null, 
				new ParameterizedTypeReference<List<Author>>() {
				});

		List<Author> authors = response.getBody();
		return authors;

	}

}
