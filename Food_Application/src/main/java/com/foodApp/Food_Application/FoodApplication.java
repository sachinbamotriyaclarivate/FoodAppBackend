package com.foodApp.Food_Application;

import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.foodApp.Food_Application.email.EmailDetails;
import com.foodApp.Food_Application.email.EmailServiceImpl;


@SpringBootApplication

public class FoodApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FoodApplication.class, args);
		//String msg,String sbj,String to,String from
	}
}
