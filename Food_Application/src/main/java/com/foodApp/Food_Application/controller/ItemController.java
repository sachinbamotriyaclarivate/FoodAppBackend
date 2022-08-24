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
import com.foodApp.Food_Application.dao.ItemsDao;
import com.foodApp.Food_Application.dto.Items;
import com.foodApp.Food_Application.service.ItemsService;
import com.foodApp.Food_Application.util.ResponseStructure;

@RestController
public class ItemController {
	@Autowired
	ItemsDao dao;

	@Autowired
	ItemsService itemsService;
	
	@PostMapping("/saveitem/{id}")
	public ResponseEntity<ResponseStructure<Items>> saveItem(@RequestBody Items item, @PathVariable int id) {
		return itemsService.saveItems(item,id);
	}
	
	@GetMapping("/item/{id}")                                                      
	public ResponseEntity<ResponseStructure<Items>> findItemsById(@PathVariable int id) {
		return itemsService.findItemById(id);
	}
	
	@GetMapping("/item")                                                      
	public ResponseEntity<ResponseStructure<List<Items>>> findAllItems(){
		return itemsService.findAllItems();
	}
	
	@PutMapping("/item/{id}")          
	public ResponseEntity<ResponseStructure<Items>> updateItems(@RequestBody Items item,  @PathVariable int id) {
		return itemsService.updateItems(item, id);
	}
	
	@DeleteMapping("/item/{id}")                                                        
	public ResponseEntity<ResponseStructure<Items>> deleteItems(@PathVariable int id) {
		return itemsService.deleteItems(id);
	}
	
}
