package com.foodApp.Food_Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.foodApp.Food_Application.dto.FoodOrder;
import com.foodApp.Food_Application.service.FoodOrderService;
import com.foodApp.Food_Application.util.ResponseStructure;

@RestController
public class FoodOrderController {
	@Autowired
	FoodOrderService foodOrderService;

	@PostMapping("/savefoodorder")
	public  ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(@RequestBody FoodOrder foodOrder, @RequestParam int items[] ,@RequestParam int staffId) {
		return foodOrderService.saveFoodOrder(foodOrder, staffId,items);
	}
	
	@GetMapping("/foodorder/{id}")                                                      
	public  ResponseEntity<ResponseStructure<FoodOrder>> findFoodOrderById(@PathVariable int id) {
		return foodOrderService.findFoodOrderById(id);
	}
	
	@GetMapping("/foodorder")                                                      
	public  ResponseEntity<ResponseStructure<List<FoodOrder>>> findAllFoodOrder(){
		return foodOrderService.findAllFoodOrder();
	}
	
	
	@DeleteMapping("/foodorder/{id}")                                                        
	public  ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(@PathVariable int id) {
		return foodOrderService.deleteFoodOrder(id);
	}
}
