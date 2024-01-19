package com.assignment.NotesApplication.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.NotesApplication.entity.Note;
import com.assignment.NotesApplication.entity.User;
import com.assignment.NotesApplication.repository.NotesRepository;

@Service
public class NotesServiceImpl implements NotesService {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private NotesRepository notesRepository;

	@Override
	public Note createNote(Note note) {
		return notesRepository.save(note);
	}
	
	@Override
	public Optional<Note> getNoteById(Long noteId) {
		return notesRepository.findById(noteId);
	}
	
	@Override
	public List<Note> getAllNotes(User user){
		return notesRepository.findAllByUser(user);
	}
	
	@Override
	public List<Note> getLast10AddedNotes(User user){
		Pageable  pageable =  PageRequest.of(0, 10);
		return notesRepository.findTop10ByUserOrderByCreatedDateDesc(user,pageable);
	}
	
	@Override
	public void deleteNoteById(Long noteId) {
		notesRepository.deleteById(noteId);
	}
	
    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void keepLast10Notes() {
		System.out.println("running.........");
	
 		String query = "DELETE n FROM note n LEFT JOIN ( SELECT id, user_id FROM ( SELECT id, user_id, created_date,ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY created_date DESC) AS row_num FROM note ) AS ranked WHERE row_num <= 10 ) AS to_keep ON n.id = to_keep.id WHERE to_keep.id IS NULL";
        entityManager.createNativeQuery(query).executeUpdate();

    }
	
	@Override
    public int getTotalNotesCount(User user) {
    	return this.notesRepository.countByUser(user);
    }
	
	
}
