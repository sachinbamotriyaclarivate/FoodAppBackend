package com.foodApp.Food_Application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodApp.Food_Application.dto.BranchManager;
import com.foodApp.Food_Application.dto.Items;
import com.foodApp.Food_Application.dto.Menu;
import com.foodApp.Food_Application.dto.Staff;
import com.foodApp.Food_Application.repository.ItemRepository;

@Repository
public class ItemsDao {
	@Autowired
	ItemRepository repository;

	public Items saveItem(Items item) {
		return repository.save(item);
	}
	public Optional<Items> findItemById(int id) {
		return repository.findById(id);
	}
	public List<Items> findAllItems() {
		return repository.findAll();
	}

	public Items updateItems(Items item, int id) {

		if (repository.findById(id).isEmpty()) {
			return null;
		} 
		else {
			Menu menu = findItemById(id).get().getMenu();
			item.setMenu(menu);
			item.setItemId(id);
			return repository.save(item);
		}
	}

	public Items deleteItems(int id) {
		Optional<Items> item = findItemById(id);
		repository.delete(item.get());
		return item.get();
	}

}
