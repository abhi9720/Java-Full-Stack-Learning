package com.restAPI.library.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restAPI.library.Backend.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
