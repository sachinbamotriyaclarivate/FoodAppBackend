package com.foodApp.Food_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.Food_Application.dto.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
	public Staff findStaffByEmailAndPassword(String email,String password);

}
