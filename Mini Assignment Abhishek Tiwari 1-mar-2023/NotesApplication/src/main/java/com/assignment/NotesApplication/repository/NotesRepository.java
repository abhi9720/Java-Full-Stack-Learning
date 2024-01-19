package com.assignment.NotesApplication.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.NotesApplication.entity.Note;
import com.assignment.NotesApplication.entity.User;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
	
    List<Note> findTop10ByUserOrderByCreatedDateDesc(User user, Pageable pageable);
    List<Note> findAllByUser(User user);
    
    int countByUser(User user);
    


}
