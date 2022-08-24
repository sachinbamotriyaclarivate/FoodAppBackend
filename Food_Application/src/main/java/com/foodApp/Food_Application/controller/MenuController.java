package com.foodApp.Food_Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.foodApp.Food_Application.dao.MenuDao;
import com.foodApp.Food_Application.dto.Menu;
import com.foodApp.Food_Application.service.MenuService;
import com.foodApp.Food_Application.util.ResponseStructure;

@RestController

public class MenuController {
	@Autowired
	MenuDao dao;

	@Autowired
	MenuService service;

	@PostMapping("/savemenu/{id}")
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(@RequestBody Menu menu, @PathVariable int id) {
		return service.saveMenu(menu,id);
	}
	
	@PutMapping("/menu/{id}")                                                      
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(@RequestBody Menu menu,@PathVariable int id) {    
		return service.updateMenu(menu, id);                                     
	}                                                                                
	                                                                                 
	@DeleteMapping("/menu/{id}")                                                        
	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(@PathVariable int id) {                               
		return service.deleteMenu(id);                                             
	}                                                                                
	@GetMapping("/menu/{id}")                                                      
	public ResponseEntity<ResponseStructure<Menu>> findMenuById(@PathVariable int id) {                             
		return service.findMenuById(id);                                           
	}                                                                                
	@GetMapping("/menu")                                                           
	public ResponseEntity<ResponseStructure<List<Menu>>> findAllMenu(){                                             
		return service.findAllMenu();                                              
	}           

}
