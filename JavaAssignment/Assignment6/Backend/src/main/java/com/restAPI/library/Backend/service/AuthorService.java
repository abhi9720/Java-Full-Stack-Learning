package com.restAPI.library.Backend.service;

import java.util.List;

import com.restAPI.library.Backend.entity.Author;
import com.restAPI.library.Backend.entity.Book;

public interface AuthorService {
	
	List<Author> getAllAuthors();
	
	public Author createAuthor(Author author);
	public Author getAuthorById(Long id);
	public Author updateAuthor(Long id, Author author);
	public boolean deleteAuthor(Long id);

}
