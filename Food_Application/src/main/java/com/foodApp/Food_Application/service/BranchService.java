package com.foodApp.Food_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.foodApp.Food_Application.dao.AdminDao;
import com.foodApp.Food_Application.dao.BranchDao;
import com.foodApp.Food_Application.dto.Admin;
import com.foodApp.Food_Application.dto.Branch;
import com.foodApp.Food_Application.exception.IdNotFoundException;
import com.foodApp.Food_Application.util.AES;
import com.foodApp.Food_Application.util.ResponseStructure;

@Service
public class BranchService {
	@Autowired
	BranchDao dao;

	@Autowired
	AES aes;

	@Autowired
	AdminDao adminDao;
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch branch,int id) {
		Admin admin =adminDao.findAdminById(id).get();
		branch.setAdmin(admin);
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		structure.setMessage("Branch Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveBranch(branch));
		return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<Branch>> findBranchById(int id) {
		Optional<Branch> optional = dao.findBranchById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("Branch Not Found ");
		} else {
			ResponseStructure<Branch> structure = new ResponseStructure<>();
			structure.setMessage("Branch Found successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);

		}
	}
	public  ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch branch, int id) {
		
		Branch brc = dao.updateBranch(branch, id);
		ResponseStructure<Branch> structure = new ResponseStructure<>();

		if (brc != null) {

			structure.setMessage("UPdated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(brc);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("invalid id");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(int id) {
		Branch branch = dao.deleteBranch(id);
		if(branch.equals(null)) {
			throw new  IdNotFoundException("Benranch Not Found");
		}
		else {
			ResponseStructure<Branch> structure = new ResponseStructure<>();
			structure.setMessage("Branch deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branch);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		}
		
	}
	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<>();
		structure.setMessage("All Branch Found successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllBranch());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.OK);
	}

}
