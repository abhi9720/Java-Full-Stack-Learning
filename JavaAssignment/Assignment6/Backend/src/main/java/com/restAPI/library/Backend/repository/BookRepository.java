package com.restAPI.library.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restAPI.library.Backend.entity.Book;


public interface BookRepository extends JpaRepository<Book, String> {

}
