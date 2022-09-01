package com.foodApp.Food_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodApp.Food_Application.dto.Admin;

@RequestMapping
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findAdminByEmailAndPassword(String email,String password);
}
