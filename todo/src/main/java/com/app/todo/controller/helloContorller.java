package com.app.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloContorller {
	
	 @GetMapping("/")
	    public ResponseEntity<String> getApiDetailsHtml() {
	        String htmlResponse = "<html><body>" +
	                "<h1>API Details:</h1>" +
	                "<ul>" +
	                "<li><strong>GET [/api/todos]</strong> - Get all Todos</li>" +
	                "<li>POST [/api/todos] - Create a Todo<br>Request Body: JSON { \"title\": \"Todo Title\", \"description\": \"Todo Description\" }</li>" +
	                "<li>GET [/api/todos/{id}] - Get a Todo by ID</li>" +
	                "<li>PUT [/api/todos/{id}] - Update a Todo<br>Request Body: JSON { \"title\": \"Updated Todo Title\", \"description\": \"Updated Todo Description\" }</li>" +
	                "<li>DELETE [/api/todos/{id}] - Delete a Todo</li>" +
	                "</ul>" +
	                "</body></html>";

	        return ResponseEntity.status(HttpStatus.OK).body(htmlResponse);
	    }

}
