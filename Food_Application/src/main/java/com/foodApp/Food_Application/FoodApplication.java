package com.foodApp.Food_Application;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = "com.foodApp.Food_Application")
@CrossOrigin(origins = "http://localhost:61556")

public class FoodApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FoodApplication.class, args);
	}
}
