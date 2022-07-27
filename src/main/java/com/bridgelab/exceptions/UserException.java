package com.bridgelab.exceptions;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public UserException(String msg, HttpStatus status) {
		super(msg);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}

//class UserNameNotFoundException extends UserException {
//	private static final long serialVersionUID = 1L;
//	}
//
//class PasswordNotFoundException extends UserException {
//	private static final long serialVersionUID = 1L;
//	}