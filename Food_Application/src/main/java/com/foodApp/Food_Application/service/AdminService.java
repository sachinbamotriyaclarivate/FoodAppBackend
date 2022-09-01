package com.foodApp.Food_Application.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodApp.Food_Application.dao.AdminDao;
import com.foodApp.Food_Application.dto.Admin;
import com.foodApp.Food_Application.exception.EmailFoundException;
import com.foodApp.Food_Application.exception.IdNotFoundException;
import com.foodApp.Food_Application.util.AES;
import com.foodApp.Food_Application.util.ResponseStructure;


@Service
public class AdminService {
	@Autowired
	AdminDao dao;

	@Autowired
	AES aes;
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		admin.setPassword(AES.encrypt(admin.getPassword(), "secret"));
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setMessage("Admin Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveAdmin(admin));
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin, int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		admin.setPassword(AES.encrypt(admin.getPassword(), "secret"));
		Admin adm = dao.updateAdmin(admin, id);
		
		if(adm!=null) {
			adm.setPassword(AES.decrypt(adm.getPassword(), "secret"));
			structure.setMessage("Updated Admin successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(adm);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		else {
			structure.setMessage("invalid id");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		
		Admin admin =dao.findAdminById(id).get();
		if(admin!=null) {
			structure.setMessage("Admin deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteAdmin(id).get());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		else {
			structure.setMessage("invalid id");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> findAdminById(int id) {
		Admin admin = dao.findAdminById(id).get();
		if (admin!=null) {
			throw new IdNotFoundException("Admin Not Found");
		} else {
			ResponseStructure<Admin> structure = new ResponseStructure<>();
			structure.setMessage("Admin Found successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(admin);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findAllAdmin(){
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		structure.setMessage("All Admin Found successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllAdmin());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unused", "static-access" })
	public ResponseEntity<ResponseStructure<Admin>> findByEmailAndPasword(Admin admin){
		
		admin.setPassword(aes.encrypt(admin.getPassword(), "secret"));
		Admin ad = dao.findByEmailAndPassword(admin);
		ResponseStructure<Admin> structure = new ResponseStructure<>();

		if(ad.equals(null)) {
			structure.setMessage("invalid id");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.NOT_FOUND);
		}
		else {
			ad.setPassword(aes.decrypt(ad.getPassword(), "secret"));
			structure.setMessage("Admin Found successfully");
			structure.setStatus(HttpStatus.OK.value());	
			structure.setT(ad);
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
		}
		
	}
	

}
