package com.bridgelab.notes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelab.model.User;
import com.bridgelab.notes.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{

	List<Note> findAllByUser_id(int user_id);

}
