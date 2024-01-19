package com.frontend.service;

import java.util.List;

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
import com.frontend.util.BookAlreadyExistsException;
import com.frontend.util.BookNotFoundException;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private RestTemplate restTemplate;

	public List<Book> getAllBooks() {
		ResponseEntity<List<Book>> response = restTemplate.exchange("http://localhost:8180/books", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Book>>() {
				});

		List<Book> books = response.getBody();
		return books;

	}

	@Override
	public Book getBookById(String id) {
		try {
			ResponseEntity<Book> response = restTemplate.exchange("http://localhost:8180/books/" + id, HttpMethod.GET,
					null, new ParameterizedTypeReference<Book>() {
					});
			if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
				// Book with the specified ID was not found"
				return null;
			}

			Book book = response.getBody();
			return book;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Book updateBook(Book book) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Book> requestEntity = new HttpEntity<>(book, headers);
		ResponseEntity<Book> response = restTemplate.exchange("http://localhost:8180/books/update", HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<Book>() {
				});

		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("-- Book updated -----");
			return response.getBody();
		} else {
			System.out.println("-- Book Not updated -----" + response.getStatusCode());
			return null;
		}

	}

	@Override
	public Book saveBook(Book book) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<Book> requestEntity = new HttpEntity<Book>(book, headers);
			ResponseEntity<Book> response = restTemplate.exchange("http://localhost:8180/books/add", HttpMethod.POST,
					requestEntity, new ParameterizedTypeReference<Book>() {
					});

			System.out.println("Book Added Successully ");
			Book result = response.getBody();
			return result;

		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.CONFLICT) {
				throw new BookAlreadyExistsException("Book With This Book Code Alraedy Exists");
			} else {
				throw ex;
			}
		}

	}

	public void deleteBook(String id) {
		try {
			ResponseEntity<Book> response = restTemplate.exchange("http://localhost:8180/books/delete/" + id,
					HttpMethod.DELETE, null, new ParameterizedTypeReference<Book>() {
					});

			HttpStatus status = response.getStatusCode();
			if (status == HttpStatus.NO_CONTENT) {
				System.out.println("Book deleted: ");
			} else {
				System.out.println("Failed to delete book with id: " + id);
			}
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new BookNotFoundException("Book with ID " + id + " not found");
			} else {
				throw ex;
			}
		}
	}

}
