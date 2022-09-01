package com.foodApp.Food_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.foodApp.Food_Application.dao.BranchManagerDao;
import com.foodApp.Food_Application.dao.StaffDao;
import com.foodApp.Food_Application.dto.BranchManager;
import com.foodApp.Food_Application.dto.Staff;
import com.foodApp.Food_Application.exception.EmailFoundException;
import com.foodApp.Food_Application.exception.IdNotFoundException;
import com.foodApp.Food_Application.util.AES;
import com.foodApp.Food_Application.util.ResponseStructure;

@Service
public class StaffService {
	@Autowired
	StaffDao dao;

	@Autowired
	AES aes;

	@Autowired
	BranchManagerDao branchManagerDao;

	public ResponseEntity<ResponseStructure<Staff>> saveStaff(Staff staff,int id) {
		BranchManager branchManager= branchManagerDao.findBranchManagerById(id).get();
		staff.setBranchManager(branchManager);

		staff.setPassword(AES.encrypt(staff.getPassword(), "secret"));
		
		ResponseStructure<Staff> structure = new ResponseStructure<>();
		structure.setMessage("Staff Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveStaff(staff));
		return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.CREATED);
	}

	
	public ResponseEntity<ResponseStructure<Staff>> findStaffById(int id) {
		Staff staff = dao.findStaffById(id).get();
		if (staff==null) {
			throw new IdNotFoundException("STaff is not found");
		} else {
			ResponseStructure<Staff> structure = new ResponseStructure<>();
			structure.setMessage("Staff Found successfully");
			structure.setStatus(HttpStatus.OK.value());
			staff.setPassword(AES.decrypt(staff.getPassword(), "secret"));
			structure.setT(staff);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
			
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Staff>>> findAllStaff(){
		ResponseStructure<List<Staff>> structure = new ResponseStructure<>();
		structure.setMessage("All Staff Found successfully");
		structure.setStatus(HttpStatus.OK.value());
		
		
		structure.setT(dao.findAllStaff());
		
		
		return new ResponseEntity<ResponseStructure<List<Staff>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Staff>> updateStaff(Staff staff, int id) {
		
		staff.setPassword(AES.encrypt(staff.getPassword(), "secret"));
		Staff st = dao.updateStaff(staff, id);
		ResponseStructure<Staff> structure = new ResponseStructure<>();

		if (st!=null) {
			structure.setMessage("Staff Member Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(st);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("invalid id");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.NOT_FOUND);
		}
}

	
	public ResponseEntity<ResponseStructure<Staff>> deleteStaff(int id) {
		ResponseStructure<Staff> structure = new ResponseStructure<>();
		structure.setMessage("Staff deleted successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteStaff(id));
		return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Staff>> findByEmailAndPassword(Staff staff){
		staff.setPassword(AES.encrypt(staff.getPassword(), "secret"));
		Staff st = dao.findByEmailAndPassword(staff);
		if(st!=null) {
			st.setPassword(AES.decrypt(st.getPassword(), "secret"));
			ResponseStructure<Staff> structure = new ResponseStructure<>();
			structure.setMessage("Staff Found successfully");
			structure.setStatus(HttpStatus.OK.value());			
			structure.setT(st);
			return new ResponseEntity<ResponseStructure<Staff>>(structure,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Staff Not Found");
		}
	}
	
	

}
