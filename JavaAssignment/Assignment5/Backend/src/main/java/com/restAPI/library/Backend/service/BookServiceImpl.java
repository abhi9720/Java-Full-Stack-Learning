package com.restAPI.library.Backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.restAPI.library.Backend.entity.Author;
import com.restAPI.library.Backend.entity.Book;
import com.restAPI.library.Backend.repository.AuthorRepository;
import com.restAPI.library.Backend.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public List<Book> getAllBook() {
		List<Book> allbooks = new ArrayList<>();
		bookRepository.findAll().forEach(allbooks::add);
		return allbooks;
	}

	@Override
	public Book getBookById(String id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public Book createBook(Book book) {
	    Long authorId = book.getAuthor().getId();
	    Author author = authorRepository.findById(authorId).orElse(null); 
	    if (author == null) {
	        return null;
	    } else {
	        	book.setAuthor(author); // Set the reference to the saved Author entity
	        	if(bookRepository.existsById(book.getId())) {
	        		throw new DataIntegrityViolationException("Book with the given id already exists");
	        	}
	            Book savedBook = bookRepository.save(book);
	            return savedBook;
	        
	    }
	}


	@Override
	public boolean deleteBook(String id) {
		if(bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Book updateBook(Book book) {
	    Optional<Book> existingBook = bookRepository.findById(book.getId());
	    
	    if (existingBook.isPresent()) {
			return bookRepository.save(book);
	    }
	    else {
	    	return null;
	    }
	}
}
