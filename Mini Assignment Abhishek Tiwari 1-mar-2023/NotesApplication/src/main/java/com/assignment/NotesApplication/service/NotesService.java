package com.assignment.NotesApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignment.NotesApplication.entity.Note;
import com.assignment.NotesApplication.entity.User;

public interface NotesService {

	Note createNote(Note note);

	Optional<Note> getNoteById(Long noteId);

	List<Note> getAllNotes(User user);

	List<Note> getLast10AddedNotes(User user);

	void deleteNoteById(Long noteId);

	int getTotalNotesCount(User user);
	

}
