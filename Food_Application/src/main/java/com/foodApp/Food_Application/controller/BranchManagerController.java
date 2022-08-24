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
import com.foodApp.Food_Application.dao.BranchManagerDao;
import com.foodApp.Food_Application.dto.BranchManager;
import com.foodApp.Food_Application.service.BranchManagerService;
import com.foodApp.Food_Application.util.ResponseStructure;

@RestController
public class BranchManagerController {
	@Autowired
	BranchManagerDao dao;

	@Autowired
	BranchManagerService service;

	@PostMapping("/savebranchmanager/{id}")
	public ResponseEntity<ResponseStructure<BranchManager>> saveBranchManager(@RequestBody BranchManager branchManager,@PathVariable int id) {
		return service.saveBranchManager(branchManager,id);
	}
	@PutMapping("/branchmanager/{id}")                                                      
	public ResponseEntity<ResponseStructure<BranchManager>> updateBranchManager(@RequestBody BranchManager branchManager,@PathVariable int id) {    
		return service.updateBranchManager(branchManager, id);                                     
	}                                                                                
	                                                                                 
	@DeleteMapping("/branchmanager")                                                        
	public ResponseEntity<ResponseStructure<BranchManager>> deleteBranchManager(@RequestParam int id) {                               
		return service.deleteBranchManager(id);                                             
	}                                                                                
	@GetMapping("/branchmanager/{id}")                                                      
	public ResponseEntity<ResponseStructure<BranchManager>> findBranchManagerById(@PathVariable int id) {                             
		return service.findBranchManagerById(id);                                           
	}                                                                                
	@GetMapping("/branchmanager")                                                           
	public ResponseEntity<ResponseStructure<List<BranchManager>>> findAllBranchManager(){                                             
		return service.findAllBranchManager();                                              
	}                                                                                
}
