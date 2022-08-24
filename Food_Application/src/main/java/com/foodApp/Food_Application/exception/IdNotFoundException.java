package com.foodApp.Food_Application.exception;


public class IdNotFoundException extends RuntimeException{
	String message = "Id not found";
	
	public String getMessage() {
		return message;
	}
	
	public IdNotFoundException(String msg) {
		this.message=msg;
	}
}