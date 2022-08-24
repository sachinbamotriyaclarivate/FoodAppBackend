package com.foodApp.Food_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.Food_Application.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
