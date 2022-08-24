package com.foodApp.Food_Application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodApp.Food_Application.dto.BranchManager;
import com.foodApp.Food_Application.dto.Menu;
import com.foodApp.Food_Application.repository.MenuRepository;

@Repository
public class MenuDao {
	@Autowired
	MenuRepository repository;

	public Menu saveMenu(Menu menu) {
		return repository.save(menu);
	}
	public Optional<Menu> findMenuById(int id) {
		return repository.findById(id);
	}
	
	public List<Menu> findAllMenu() {
		return repository.findAll();
	}

	public Menu updateMenu(Menu menu, int id) {

		if (repository.findById(id).isEmpty()) {
			return null;
		} 
		else {
			BranchManager branchManager = findMenuById(id).get().getBranchManager();
			menu.setBranchManager(branchManager);
			menu.setMenuId(id);
			return repository.save(menu);
		}
	}

	public Menu deleteMenu(int id) {
		Optional<Menu> menu = findMenuById(id);
		repository.delete(menu.get());
		return menu.get();
	}
}
