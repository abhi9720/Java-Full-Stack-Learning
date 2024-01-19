package com.restAPI.library.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restAPI.library.Backend.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmail(String email);
}
