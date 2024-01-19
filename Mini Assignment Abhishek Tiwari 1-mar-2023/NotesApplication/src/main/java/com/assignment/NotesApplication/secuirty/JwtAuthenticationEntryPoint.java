package com.assignment.NotesApplication.secuirty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
 
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
		
		System.out.println(request);
		System.out.println(response);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized Access");
		
	}

}
