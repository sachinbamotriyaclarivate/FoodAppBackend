package com.foodApp.Food_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.Food_Application.dto.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
