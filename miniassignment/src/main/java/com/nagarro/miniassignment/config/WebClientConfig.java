package com.nagarro.miniassignment.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
	

	
    
	// WebClient bean for API1
	@Bean("randomUserApiWebClient")
	public WebClient api1WebClient() {

		return WebClient.builder()
				.baseUrl("https://randomuser.me/api/")
				.clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
						.responseTimeout(Duration.ofMillis(2000))
						.doOnConnected(conn -> conn
								.addHandlerLast(new ReadTimeoutHandler(2000, TimeUnit.MILLISECONDS))
								.addHandlerLast(new WriteTimeoutHandler(2000, TimeUnit.MILLISECONDS)))))
				.build();
	}

	@Bean("nationalityApiWebClient")
	public WebClient api2WebClient() {
		return WebClient.builder()
				.baseUrl("https://api.nationalize.io/")
				.clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
						.responseTimeout(Duration.ofMillis(1000))
						.doOnConnected(conn -> conn
								.addHandlerLast(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS))
								.addHandlerLast(new WriteTimeoutHandler(1000, TimeUnit.MILLISECONDS)))))
				.build();
	}

	// WebClient bean for API3
	@Bean("genderApiWebClient")
	public WebClient api3WebClient() {
		return WebClient.builder()
				.baseUrl("https://api.genderize.io/")
				.clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
						.responseTimeout(Duration.ofMillis(1000))
						.doOnConnected(conn -> conn
								.addHandlerLast(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS))
								.addHandlerLast(new WriteTimeoutHandler(1000, TimeUnit.MILLISECONDS)))))
				.build();
	}

}
