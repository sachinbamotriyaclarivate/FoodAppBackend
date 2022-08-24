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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodApp.Food_Application.dao.StaffDao;
import com.foodApp.Food_Application.dto.Staff;
import com.foodApp.Food_Application.service.StaffService;
import com.foodApp.Food_Application.util.ResponseStructure;

@RestController
public class StaffController {

	@Autowired
	StaffDao dao;

	@Autowired
	StaffService service;

	@PostMapping("/savestaff/{id}")
	public ResponseEntity<ResponseStructure<Staff>> saveStaff(@RequestBody Staff staff, @PathVariable int id) {
		return service.saveStaff(staff,id);
	}
	
	@PutMapping("/staff/{id}")                                                      
	public ResponseEntity<ResponseStructure<Staff>> updateStaff(@RequestBody Staff Staff,@PathVariable int id) {    
		return service.updateStaff(Staff, id);                                     
	}                                                                                
	                                                                                 
	@DeleteMapping("/staff/{id}")                                                        
	public ResponseEntity<ResponseStructure<Staff>> deleteStaff(@PathVariable int id) {                               
		return service.deleteStaff(id);                                             
	}                                                                                
	@GetMapping("/staff/{id}")                                                      
	public ResponseEntity<ResponseStructure<Staff>> findStaffById(@PathVariable int id) {                             
		return service.findStaffById(id);                                           
	}                                                                                
	@GetMapping("/staff")                                                           
	public ResponseEntity<ResponseStructure<List<Staff>>> findAllStaff(){                                             
		return service.findAllStaff();                                              
	}                    
}
