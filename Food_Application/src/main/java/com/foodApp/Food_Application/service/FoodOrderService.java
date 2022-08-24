package com.foodApp.Food_Application.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodApp.Food_Application.dao.FoodOrderDao;
import com.foodApp.Food_Application.dao.ItemsDao;
import com.foodApp.Food_Application.dao.StaffDao;
import com.foodApp.Food_Application.dto.FoodOrder;
import com.foodApp.Food_Application.dto.Items;
import com.foodApp.Food_Application.dto.Staff;
import com.foodApp.Food_Application.email.EmailDetails;
import com.foodApp.Food_Application.email.EmailServiceImpl;
import com.foodApp.Food_Application.exception.IdNotFoundException;
import com.foodApp.Food_Application.util.ResponseStructure;

@Service
public class FoodOrderService {
	@Autowired
	FoodOrderDao dao;

	@Autowired
	StaffDao staffDao;

	@Autowired
	ItemsDao itemsDao;

	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder, int id,int item[])  {
		
	
		List<Items> items = new ArrayList<>();
		int countItem=0;
		int amount=0;
		for(int itemId:item) {
			Items it=itemsDao.findItemById(itemId).get();
			amount+=Integer.parseInt(it.getPrice());
			items.add(it);
			countItem++;
		}
		
		
		
		
		foodOrder.setQuantity(countItem);
		foodOrder.setTotalAmount(amount);
		foodOrder.setItem(items);
		foodOrder.setOrderCreatedTime(""+LocalTime.now());

		Staff staff = staffDao.findStaffById(id).get();
		foodOrder.setStaff(staff);
		
		
		EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
		EmailDetails details =new EmailDetails();
		details.setRecipient("sachinbamotriya91@gmail.com");
		details.setSubject("Welcome to Food App");
		String Itemmsg=details.setEmail(items, foodOrder);
		//details.setItemsMsg(Itemmsg);
		details.setMsgBody(Itemmsg);
		try {
			emailServiceImpl.sendSimpleMail(details);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseStructure<FoodOrder> structure = new ResponseStructure<>();
		structure.setMessage("Food ordered has been saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveFoodOrder(foodOrder));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.CREATED);
			}
//	Staff staff = findFoodOrderById(id).getStaff();
//	foodOrder.setStaff(staff);
//	
//	List<Items> item =findFoodOrderById(id).getItem();
//	foodOrder.setId(id);
//	foodOrder.setItem(item);
	public ResponseEntity<ResponseStructure<FoodOrder>> findFoodOrderById(int id) {
		
		
		Optional<FoodOrder> optional = dao.findFoodOrderById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("Order Not Found");
		} else {
			ResponseStructure<FoodOrder> structure = new ResponseStructure<>();
			structure.setMessage("FoodOrder Found successfully By Id");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);

		}
	}
	
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> findAllFoodOrder(){
		ResponseStructure<List<FoodOrder>> structure = new ResponseStructure<>();
		structure.setMessage("All FoodOrder Found successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllFoodOrder());
		return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(int id) {
		
		ResponseStructure<FoodOrder> structure = new ResponseStructure<>();
		structure.setMessage("FoodOrder deleted successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteFoodOrder(id));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
	}
}
