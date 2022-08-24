package com.foodApp.Food_Application.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.foodApp.Food_Application.dao.AdminDao;
import com.foodApp.Food_Application.dto.Admin;
import com.foodApp.Food_Application.service.AdminService;
import com.foodApp.Food_Application.util.ResponseStructure;

@RestController
public class AdminController {
	@Autowired
	AdminDao dao;

	@Autowired
	AdminService service;
	@PostMapping("/admin")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
		return service.saveAdmin(admin);
	}
	@PutMapping("/admin/{id}")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin,@PathVariable int id) {
		return service.updateAdmin(admin, id);
	}

	@DeleteMapping("/admin")
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(@RequestParam int id) {
		return service.deleteAdmin(id);
	}
	@GetMapping("/admin/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findAdminById(@PathVariable int id) {
		return service.findAdminById(id);
	}
	@GetMapping("/admin")
	public ResponseEntity<ResponseStructure<List<Admin>>>  findAllAdmin(){
		return service.findAllAdmin();
	}
}