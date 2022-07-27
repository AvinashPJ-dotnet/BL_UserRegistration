package com.bridgelab.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {

	@ExceptionHandler(value = UserException.class)
	public ResponseEntity<Object> exception(UserException userException) {
		return new ResponseEntity<>(userException.getMessage(), userException.getStatus());
	}

}
