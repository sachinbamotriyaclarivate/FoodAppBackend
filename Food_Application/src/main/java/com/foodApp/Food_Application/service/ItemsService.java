package com.foodApp.Food_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodApp.Food_Application.dao.ItemsDao;
import com.foodApp.Food_Application.dao.MenuDao;
import com.foodApp.Food_Application.dto.Branch;
import com.foodApp.Food_Application.dto.Items;
import com.foodApp.Food_Application.dto.Menu;
import com.foodApp.Food_Application.exception.IdNotFoundException;
import com.foodApp.Food_Application.util.ResponseStructure;
@Service
public class ItemsService {

	@Autowired
	ItemsDao dao;

	@Autowired
	MenuDao menuDao;

	public ResponseEntity<ResponseStructure<Items>> saveItems(Items item,int id) {
		Menu menu = menuDao.findMenuById(id).get();
		item.setMenu(menu);
		
		ResponseStructure<Items> structure = new ResponseStructure<>();
		structure.setMessage("Items Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveItem(item));
		return new ResponseEntity<ResponseStructure<Items>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Items>> findItemById(int id) {
		Optional<Items> optional =  dao.findItemById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("Item not Found");
		} else {
			ResponseStructure<Items> structure = new ResponseStructure<>();
			structure.setMessage("Items Found successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Items>>(structure, HttpStatus.OK);
		}
			}
	
	public ResponseEntity<ResponseStructure<List<Items>>> findAllItems(){
		ResponseStructure<List<Items>> structure = new ResponseStructure<>();
		structure.setMessage("All Items Found successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllItems());
		return new ResponseEntity<ResponseStructure<List<Items>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Items>> updateItems(Items item, int id) {
		Items it = dao.updateItems(item, id);
		ResponseStructure<Items> structure = new ResponseStructure<>();

		if (it != null) {

			structure.setMessage("UPdated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(it);
			return new ResponseEntity<ResponseStructure<Items>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("invalid id");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Items>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Items>> deleteItems(int id) {
		Items item = dao.deleteItems(id);
		if(item.equals(null)) {
			throw new  IdNotFoundException("item Not Found");
		}
		else {
			ResponseStructure<Items> structure = new ResponseStructure<>();
			structure.setMessage("Items deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(item);
			return new ResponseEntity<ResponseStructure<Items>>(structure, HttpStatus.OK);
		}
	
	}
}
