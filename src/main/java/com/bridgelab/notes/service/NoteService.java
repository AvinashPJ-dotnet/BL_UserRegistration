package com.bridgelab.notes.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelab.dto.ResponseDTO;
import com.bridgelab.exceptions.UserException;
import com.bridgelab.model.User;
import com.bridgelab.notes.model.Note;
import com.bridgelab.notes.repository.NoteRepository;
import com.bridgelab.repository.UserRepository;
import com.bridgelab.utility.ElasticSearchService;

@Service
public class NoteService implements INoteService {

	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ElasticSearchService elasticService;

	@Override
	public ResponseDTO addNote(Note note,int id) {
		User user=userRepository.getById(id);
		Note newNote=new Note(note.getTitle(), note.getNoteText(),user);
		Note note2=noteRepository.save(newNote);
		elasticService.createNote(note2);
		return new ResponseDTO(note2,"",200);
	}

	@Override
	public ResponseDTO getNotes(int id) {
		return new ResponseDTO(noteRepository.findAll(),"",200);
	}

	@Override
	public ResponseDTO FindAllByUser_id(int id) {
		return new ResponseDTO(noteRepository.findAllByUser_id(id),"",200);
	}

	@Override
	public ResponseDTO deleteNote(int id) {
		// TODO Auto-generated method stub
		Note note=noteRepository.getById(id);
		if(!note.equals(null)) {
			note.setDeleted(true);
			note.setDeletedDate(LocalDate.now());
			return new ResponseDTO(noteRepository.save(note),"Deleted",200);
		}
		throw new UserException("Note not found", HttpStatus.NOT_FOUND);
		
	}

	@Override
	public ResponseDTO trashNote(int userId) {
	List<Note> trashNotes=	noteRepository.findAllByUser_id(userId).stream().filter(note->note.isDeleted()==true).collect(Collectors. toList());
		// TODO Auto-generated method stub
		return  new ResponseDTO(trashNotes,"",200);
	}

}
