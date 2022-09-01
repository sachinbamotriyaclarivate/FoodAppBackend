package com.foodApp.Food_Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.foodApp.Food_Application.dao.BranchDao;

import com.foodApp.Food_Application.dto.Branch;
import com.foodApp.Food_Application.service.BranchService;
import com.foodApp.Food_Application.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BranchController {
	@Autowired
	BranchDao dao;

	@Autowired
	BranchService service;

	@PostMapping("/branch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody Branch branch,@PathVariable int id) {

		return service.saveBranch(branch,id);
	}
	
	@PutMapping("/branch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody Branch branch,@PathVariable int id) {
		return service.updateBranch(branch, id);
	}

	@DeleteMapping("/branch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(@PathVariable int id) {
		return service.deleteBranch(id);
	}
	@GetMapping("/branch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> findBranchById(@PathVariable int id) {
		return service.findBranchById(id);
	}
	@GetMapping("/branch")
	public  ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch(){
		return service.findAllBranch();
	}
}
