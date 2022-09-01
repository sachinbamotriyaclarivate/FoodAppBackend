package com.foodApp.Food_Application.exception;

public class EmailFoundException   extends RuntimeException{
String message = "Email Found";
	
	public String getMessage() {
		return message;
	}
	
	

}
