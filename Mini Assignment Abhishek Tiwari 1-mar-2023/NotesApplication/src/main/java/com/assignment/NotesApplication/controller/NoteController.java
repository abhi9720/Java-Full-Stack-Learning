package com.assignment.NotesApplication.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.NotesApplication.dto.NoteResponse;
import com.assignment.NotesApplication.entity.Note;
import com.assignment.NotesApplication.entity.User;
import com.assignment.NotesApplication.secuirty.SecurityUtils;
import com.assignment.NotesApplication.service.NotesService;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

	@Autowired
	private NotesService notesService;

	@Autowired
	private SecurityUtils securityUtils;

	@PostMapping
	public ResponseEntity<?> createNote(@RequestBody Note note) {

		User currentUser = securityUtils.getCurrentUser();
		if (currentUser == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		note.setUser(currentUser);
		note.setCreatedDate(new Date());

		Note createdNote = notesService.createNote(note);

		NoteResponse noteResponse = new NoteResponse();
		noteResponse.setNoteId(createdNote.getId());
		noteResponse.setContent(createdNote.getContent());
		noteResponse.setCreatedDate(createdNote.getCreatedDate());
		noteResponse.setUserID(createdNote.getUser().getId());
		noteResponse.setUsername(createdNote.getUser().getName());

		return new ResponseEntity<>(noteResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getNoteById(@PathVariable Long id){
		Optional<Note> noteOptional = notesService.getNoteById(id);

		if (!noteOptional.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		User currentUser = securityUtils.getCurrentUser();

		User noteCreator = noteOptional.get().getUser();

		if (!currentUser.getId().equals(noteCreator.getId())) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);  
		}
		
		Note notefound = noteOptional.get();

		NoteResponse noteResponse =  new NoteResponse();
		noteResponse.setNoteId(notefound.getId());
		noteResponse.setContent(notefound.getContent());
		noteResponse.setCreatedDate(notefound.getCreatedDate());
		noteResponse.setUserID(notefound.getUser().getId());
		noteResponse.setUsername(notefound.getUser().getName());

		return new ResponseEntity<>(noteResponse, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Note>> getAllNotes(){
		User currentUser = securityUtils.getCurrentUser();
		if(currentUser==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<Note> notes =  notesService.getAllNotes(currentUser);
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
		Optional<Note> noteOptional = notesService.getNoteById(id);

		if (!noteOptional.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		User currentUser = securityUtils.getCurrentUser();

		User noteCreator = noteOptional.get().getUser();

		if (!currentUser.getId().equals(noteCreator.getId())) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN); // You may choose to return 403 Forbidden
		}

		notesService.deleteNoteById(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/recent")
	public ResponseEntity<?> getLast10AddedNotes() {
		User currentUser = securityUtils.getCurrentUser();
		if (currentUser == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<Note> recentNotes = notesService.getLast10AddedNotes(currentUser);
		int totalNotesCount =  notesService.getTotalNotesCount(currentUser);
		Map response = new HashMap();
		response.put("totalCount", totalNotesCount);
		response.put("recentnotes", recentNotes);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
