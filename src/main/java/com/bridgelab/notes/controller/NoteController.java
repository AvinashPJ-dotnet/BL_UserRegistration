package com.bridgelab.notes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.dto.ResponseDTO;
import com.bridgelab.jwtutils.TokenManager;
import com.bridgelab.notes.model.Note;
import com.bridgelab.notes.service.INoteService;

@RestController
@RequestMapping("/api/note")
public class NoteController {

	@Autowired
	INoteService noteService;

	@PostMapping
	public ResponseEntity<ResponseDTO> CreateNote(@RequestHeader(value = "Authorization") String token,
			@RequestBody Note note) {
		int id = TokenManager.getUsernameFromToken(token);
		return new ResponseEntity<>(noteService.addNote(note, id), HttpStatus.OK);
	}

//	@GetMapping
//	public ResponseEntity<ResponseDTO> getAllNotes(@RequestHeader(value = "Authorization") String token) {
//		int id = TokenManager.getUsernameFromToken(token);
//		return new ResponseEntity<>(noteService.getNotes(id), HttpStatus.OK);
//	}

	@GetMapping
	public ResponseEntity<ResponseDTO> getAllNotesByUserId(@RequestHeader(value = "Authorization") String token) {
		int id = TokenManager.getUsernameFromToken(token);
		return new ResponseEntity<>(noteService.FindAllByUser_id(id), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> deleteNote(@RequestHeader(value = "Authorization") String token,
			@RequestParam("id") int id) {
		return new ResponseEntity<>(noteService.deleteNote(id), HttpStatus.OK);
	}

	@GetMapping("/trash")
	public ResponseEntity<ResponseDTO> getTrashNotes(@RequestHeader(value = "Authorization") String token) {
		int userId = TokenManager.getUsernameFromToken(token);
		return new ResponseEntity<>(noteService.trashNote(userId), HttpStatus.OK);
	}

}
