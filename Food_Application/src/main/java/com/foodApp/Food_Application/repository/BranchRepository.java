package com.foodApp.Food_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.Food_Application.dto.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
