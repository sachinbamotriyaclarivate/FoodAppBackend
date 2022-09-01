package com.foodApp.Food_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.foodApp.Food_Application.dao.BranchDao;
import com.foodApp.Food_Application.dao.BranchManagerDao;
import com.foodApp.Food_Application.dto.Admin;
import com.foodApp.Food_Application.dto.Branch;
import com.foodApp.Food_Application.dto.BranchManager;
import com.foodApp.Food_Application.exception.EmailFoundException;
import com.foodApp.Food_Application.exception.IdNotFoundException;
import com.foodApp.Food_Application.util.AES;
import com.foodApp.Food_Application.util.ResponseStructure;
@Service
public class BranchManagerService {

	@Autowired
	BranchManagerDao dao;

	@Autowired
	AES aes;

	@Autowired
	BranchDao branchDao;
	public ResponseEntity<ResponseStructure<BranchManager>> saveBranchManager(BranchManager branchManager,int id) {
		Branch branch = branchDao.findBranchById(id).get();
		branchManager.setBranch(branch);
		branchManager.setPassword(AES.encrypt(branchManager.getPassword(), "secret"));
		ResponseStructure<BranchManager> structure = new ResponseStructure<>();
		structure.setMessage("Branch Manager Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveBranchManager(branchManager));
		return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.CREATED);
	}
	public  ResponseEntity<ResponseStructure<BranchManager>> findBranchManagerById(int id) {
		
		Optional<BranchManager> optional = dao.findBranchManagerById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("Branch Manager Not Found");
		} else {
			ResponseStructure<BranchManager> structure = new ResponseStructure<>();
			structure.setMessage("Branch Manager Found successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);

		}
	}
	
	public ResponseEntity<ResponseStructure<BranchManager>> updateBranchManager(BranchManager branchManager, int id) {
		Branch branch =branchDao.findBranchById(id).get();
		branchManager.setBranch(branch);
		branchManager.setPassword(AES.encrypt(branchManager.getPassword(), "secret"));
		BranchManager bm=dao.updateBranchManager(branchManager, id);
		ResponseStructure<BranchManager> structure = new ResponseStructure<>();

		if (bm != null) {
			structure.setMessage("BranchManager Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(bm);
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("invalid id");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<BranchManager>> deleteBranchManager(int id) {
		BranchManager branchManager=dao.deleteBranchManager(id);
		if(branchManager.equals(null)) {
			throw  new IdNotFoundException("Branch Manager Not Found");
		}
		else {
			ResponseStructure<BranchManager> structure = new ResponseStructure<>();
			structure.setMessage("BranchManager deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branchManager);
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		}
	}
	public ResponseEntity<ResponseStructure<List<BranchManager>>> findAllBranchManager(){
		ResponseStructure<List<BranchManager>> structure = new ResponseStructure<>();
		structure.setMessage("All BranchManager Found successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllBranchManager());
		return new ResponseEntity<ResponseStructure<List<BranchManager>>>(structure, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<BranchManager>> findByEmailAndPassword(BranchManager branchManager){
		branchManager.setPassword(AES.encrypt(branchManager.getPassword(), "secret"));
		BranchManager bm = dao.findByEmailAndPassword(branchManager);
		if(bm!=null) {
			bm.setPassword(AES.decrypt(bm.getPassword(), "secret"));
			ResponseStructure<BranchManager> structure = new ResponseStructure<>();
			structure.setMessage("Branch Manager  Found successfully");
			structure.setStatus(HttpStatus.OK.value());			
			structure.setT(bm);
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Branch Manager Not Found");
		}
		
	}
}
