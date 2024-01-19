package com.restAPI.library.Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restAPI.library.Backend.entity.Book;
import com.restAPI.library.Backend.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			List<Book> result = bookService.getAllBook();
			if (result.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBookById(@PathVariable String id) {
		try {
			Book result = bookService.getBookById(id);
			if (result == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("{\"message\": \"Book Not Found\"}");
			} else {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/books/add")
	public ResponseEntity<?> createBook(@RequestBody Book book) {
	    try {
	        Book result = bookService.createBook(book);
	        if (result == null) {
	            // Return a 404 Not Found response if the Book could not be created due to no Author
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("{\"message\": \"Invalid author or author not found\"}");
	        } else {
	            return new ResponseEntity<>(result, HttpStatus.OK);
	        }
	    } catch (DataIntegrityViolationException e) {
	        // Return a 409 Conflict response if the primary key already exists
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("{\"message\": \"Book with the given Book Code already exists\"}");
	    } catch (Exception e) {
	        // Return a 500 Internal Server Error response for any other exception
	        e.printStackTrace();
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PutMapping("/books/update")
	public ResponseEntity<?> updateBook(@RequestBody Book book) {
		try {
			Book result = bookService.updateBook(book);
			if (result == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("{\"message\": \"Book Not Found\"}");
			} else {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/books/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable String id){
		 try {
		        boolean deleted = bookService.deleteBook(id);
		        if (deleted) {
		            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    } catch (Exception e) {
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

}
