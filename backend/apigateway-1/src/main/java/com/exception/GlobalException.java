package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(UnauthorisedException.class)
	public ResponseEntity<?> sendError(UnauthorisedException e){
		return new ResponseEntity<>(e.toString(),HttpStatus.UNAUTHORIZED);
	}
}
