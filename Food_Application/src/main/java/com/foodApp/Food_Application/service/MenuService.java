package com.foodApp.Food_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.foodApp.Food_Application.dao.BranchManagerDao;
import com.foodApp.Food_Application.dao.MenuDao;
import com.foodApp.Food_Application.dto.BranchManager;
import com.foodApp.Food_Application.dto.Menu;
import com.foodApp.Food_Application.exception.IdNotFoundException;
import com.foodApp.Food_Application.util.ResponseStructure;

@Service

public class MenuService {
	@Autowired
	MenuDao dao;

	@Autowired
	BranchManagerDao branchManagerDao;

	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu,int id) {
		BranchManager branchManager= branchManagerDao.findBranchManagerById(id).get();
		menu.setBranchManager(branchManager);
		ResponseStructure<Menu> structure = new ResponseStructure<>();
		structure.setMessage("Menu Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveMenu(menu));
		return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.CREATED); 
	}
	public ResponseEntity<ResponseStructure<Menu>> findMenuById(int id) {
		Optional<Menu> optional = dao.findMenuById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("Menu Not FOund");
		} else {
			ResponseStructure<Menu> structure = new ResponseStructure<>();
			structure.setMessage("Menu Found successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);

		}
	}
	
	public ResponseEntity<ResponseStructure<List<Menu>>> findAllMenu(){
		ResponseStructure<List<Menu>> structure = new ResponseStructure<>();
		structure.setMessage("All Menu Found successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllMenu());
		return new ResponseEntity<ResponseStructure<List<Menu>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu menu, int menuId) {
		Menu mn = dao.updateMenu(menu,menuId);
		ResponseStructure<Menu> structure = new ResponseStructure<>();

		if (mn != null) {
			structure.setMessage("Menu Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(mn);
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("invalid id");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int id) {
		ResponseStructure<Menu> structure = new ResponseStructure<>();
		structure.setMessage("Menu deleted successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteMenu(id));
		return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
	}
}
