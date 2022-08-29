package com.bridgelab.notes.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bridgelab.model.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@JsonAutoDetect
@Getter
@Setter
@ToString
@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String noteText;
	private boolean isArchived;
	private boolean isDeleted;
	private Date createdDate;
	private LocalDate deletedDate;
	
	@ManyToOne
	private User user;

	public Note() {
	}

	public Note(String title, String noteText, User user) {
		super();
		this.title = title;
		this.noteText = noteText;
		this.user = user;
		long millis = System.currentTimeMillis();
		createdDate = new Date(millis);
	}

	// constructor overloading
	public Note(String title, String noteText, User user, boolean archived) {
		super();
		this.title = title;
		this.noteText = noteText;
		this.user = user;
		long millis = System.currentTimeMillis();
		createdDate = new Date(millis);
		this.isArchived = archived;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	@JoinTable(name = "userNotes", joinColumns = { @JoinColumn(name = "note_id") }, inverseJoinColumns = {
//	@JoinColumn(name = "user_id") })
	

}
