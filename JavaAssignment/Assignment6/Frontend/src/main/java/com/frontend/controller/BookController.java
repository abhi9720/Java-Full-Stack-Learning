package com.frontend.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.frontend.dto.Author;
import com.frontend.dto.Book;
import com.frontend.service.AuthorService;
import com.frontend.service.BookService;
import com.frontend.util.BookAlreadyExistsException;
import com.frontend.util.BookNotFoundException;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;
	
	@RequestMapping("/")
	public String Indexpage() {
		return "index";
	}

	// Get ALl Books 
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String showAllBooks(ModelMap model) throws IOException {
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		return "books";
	}

	// Get Edit Book Page
	@RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
	public String ShowBookUpdatePage(@PathVariable String id, ModelMap model) {
		Book book = bookService.getBookById(id);
		if (book == null) {
	        throw new BookNotFoundException();
		}

		List<Author> authors = authorService.getAllAuthors();

		model.addAttribute("book", book);
		model.addAttribute("authors", authors);
		return "editbook";
	}

	// Update  Book
	@RequestMapping(value = "/books/edit", method = RequestMethod.POST)
	public String updateBook(HttpServletRequest request) throws ParseException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Date dateAdded = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
				.parse(request.getParameter("dateAdded"));
		Long authorId = Long.parseLong(request.getParameter("author.id"));

		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setDateAdded(dateAdded);
		Author author = new Author();
		author.setId(authorId);
		book.setAuthor(author);
		bookService.updateBook(book);
		return "redirect:/books";
	}

	// Delete Book
	@RequestMapping("/books/delete/{id}")
	public String deleteBooK(@PathVariable String id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}

	//Get Add Book Page
	@RequestMapping("/books/add")
	public String ShowBookAddPage(ModelMap model) {
		List<Author> authors = authorService.getAllAuthors();
		model.addAttribute("authors", authors);
		return "addbook";
	}

	// Add New Book
	@RequestMapping(value = "/books/add", method = RequestMethod.POST)
	public String saveBook(HttpServletRequest request, ModelMap model) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Long authorId = Long.parseLong(request.getParameter("author.id"));

		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setDateAdded(new Date());

		Author author = new Author();
		author.setId(authorId);
		book.setAuthor(author);

		try {
			bookService.saveBook(book);
			return "redirect:/books";
		}
		catch(BookAlreadyExistsException e) {
			// on same page show error MSG
			List<Author> authors = authorService.getAllAuthors();
			model.addAttribute("authors", authors);
			model.addAttribute("book", book);
			model.addAttribute("bookConflict", "Book With This Book Code already Exits");
			return "addbook";
		}

		
	}
	
	// Handler to handle if user request For Book Not Exists
	@ExceptionHandler(BookNotFoundException.class)
	public ModelAndView handleBookNotFoundException() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("bookNotFound");
	    return mav;
	}

	// For Invalid URL
	@RequestMapping("/**")
	public String handle404() {
		return "404";
	}
}
