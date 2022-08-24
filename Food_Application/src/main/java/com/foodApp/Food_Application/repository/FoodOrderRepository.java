package com.foodApp.Food_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.Food_Application.dto.FoodOrder;

public interface FoodOrderRepository  extends JpaRepository<FoodOrder, Integer>{

}
