package com.bridgelab.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import com.bridgelab.notes.model.Note;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@JsonAutoDetect
@Getter
@Setter
@ToString
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String firstName;
	private String lastName;

	@NotBlank(message = "Email is mandatory")
	@Email
	private String email;

	@NotBlank(message = "Username is mandatory")
	private String username;

	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.+[0-9])(?=.*[%^<>?/:'}{()*!|.,;_#&$+=@]).{8,}$")
	private String password;

	private boolean isVerified = false;

//	@JsonIgnore
//	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "users")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(cascade = CascadeType.ALL)
	private List<Note> notes;

	public User() {
	}

	public User(String firstName, String lastName, String email, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//				+ ", username=" + username + ", password=" + password + ", isVerified=" + isVerified + "]";
//	}

}
