package com.bridgelab.notes.service;

import com.bridgelab.dto.ResponseDTO;
import com.bridgelab.notes.model.Note;

public interface INoteService {
	ResponseDTO addNote(Note note, int id);

	ResponseDTO getNotes(int id);

	ResponseDTO FindAllByUser_id(int id);

	ResponseDTO deleteNote(int id);

	ResponseDTO trashNote(int userId);

}
