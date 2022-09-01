package com.foodApp.Food_Application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.foodApp.Food_Application.dto.FoodOrder;
import com.foodApp.Food_Application.dto.Staff;
import com.foodApp.Food_Application.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {
	@Autowired
	FoodOrderRepository repository;
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {

		return repository.save(foodOrder);
	}
	public Optional<FoodOrder> findFoodOrderById(int id) {
		return repository.findById(id);
	}
	
	public List<FoodOrder> findAllFoodOrder() {
		return repository.findAll();
	}
	
	public FoodOrder updateFoodOrder(FoodOrder foodOrder,int orderId) {

		if (repository.findById(orderId).isEmpty()) {
			return null;
		} 
		else {
			foodOrder.setId(orderId);
			Staff staff= findFoodOrderById(orderId).get().getStaff();
			foodOrder.setStaff(staff);
			return repository.save(foodOrder);
		}
	}

	public FoodOrder deleteFoodOrder(int id) {
		FoodOrder foodOrder = findFoodOrderById(id).get();
		repository.delete(foodOrder);
		return foodOrder;
	}
}
