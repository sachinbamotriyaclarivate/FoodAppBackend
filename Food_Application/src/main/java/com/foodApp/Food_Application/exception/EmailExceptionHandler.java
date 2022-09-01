package com.foodApp.Food_Application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foodApp.Food_Application.util.ResponseStructure;

@ControllerAdvice

public class EmailExceptionHandler   extends ResponseEntityExceptionHandler {
	@ExceptionHandler(EmailFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundException(EmailFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Email Found");
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setT("Email exists");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
}
