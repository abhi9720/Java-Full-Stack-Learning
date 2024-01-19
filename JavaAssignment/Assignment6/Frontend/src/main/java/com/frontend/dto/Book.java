package com.frontend.dto;

import java.util.Date;


public class Book {
	
	private String id;
	private String Name;
	private Author author;
	private Date dateAdded;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", Name=" + Name + ", author=" + author + ", dateAdded=" + dateAdded + "]";
	}
	
	
	
	
	


}
