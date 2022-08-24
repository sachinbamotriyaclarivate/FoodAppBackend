package com.foodApp.Food_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.Food_Application.dto.Items;

public interface ItemRepository extends JpaRepository<Items, Integer> {

}
