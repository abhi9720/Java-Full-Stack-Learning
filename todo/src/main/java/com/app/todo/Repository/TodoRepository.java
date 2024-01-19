package com.app.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
