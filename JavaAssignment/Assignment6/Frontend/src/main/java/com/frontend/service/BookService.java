package com.frontend.service;

import java.util.List;

import com.frontend.dto.Book;

public interface BookService {
	List<Book> getAllBooks();

	Book getBookById(String id);

	Book saveBook(Book book);

	Book updateBook(Book book);

	void deleteBook(String id);

}
