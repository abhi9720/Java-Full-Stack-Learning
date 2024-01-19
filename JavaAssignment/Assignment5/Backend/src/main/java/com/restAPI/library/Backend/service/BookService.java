package com.restAPI.library.Backend.service;

import java.util.List;

import com.restAPI.library.Backend.entity.Book;

public interface BookService {

	public List<Book> getAllBook();
	
	public Book createBook(Book book);
	public Book getBookById(String id);
	public Book updateBook(Book book);
	public boolean deleteBook(String id);
	
}
