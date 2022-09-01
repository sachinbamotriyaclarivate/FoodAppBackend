package com.foodApp.Food_Application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodApp.Food_Application.dto.Admin;
import com.foodApp.Food_Application.repository.AdminRepository;


@Repository
public class AdminDao {
	@Autowired
	AdminRepository repository;

	public Admin saveAdmin(Admin admin) {
		return repository.save(admin);
	}

	public Admin updateAdmin(Admin admin, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			admin.setAdminId(id);
			return repository.save(admin);
		}
	}
	public Optional<Admin> deleteAdmin(int id) {
		Optional<Admin> admin = findAdminById(id);
		repository.delete(admin.get());
		return admin;
	}

	public Optional<Admin> findAdminById(int id) {
		return repository.findById(id);
	}

	public List<Admin> findAllAdmin() {
		return repository.findAll();
	}
	
	public Admin findByEmailAndPassword(Admin admin){
		String email =admin.getEmail();
		String password = admin.getPassword();
		return repository.findAdminByEmailAndPassword(email,password);
	}
	

}
