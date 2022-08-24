package com.foodApp.Food_Application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodApp.Food_Application.dto.Branch;
import com.foodApp.Food_Application.dto.BranchManager;
import com.foodApp.Food_Application.dto.Staff;
import com.foodApp.Food_Application.repository.StaffRepository;

@Repository
public class StaffDao {
	@Autowired
	StaffRepository repository;

	public Staff saveStaff(Staff staff) {
		return repository.save(staff);
	}

	public Optional<Staff> findStaffById(int id) {
		return repository.findById(id);
	}

	public List<Staff> findAllStaff() {
		return repository.findAll();
	}

	public Staff updateStaff(Staff staff, int id) {

		if (repository.findById(id).isEmpty()) {
			return null;
		} 
		else {
			BranchManager branchManager = findStaffById(id).get().getBranchManager();
			staff.setBranchManager(branchManager);
			staff.setStaffId(id);
			return repository.save(staff);
		}
	}

	public Staff deleteStaff(int id) {
		Optional<Staff> staff = findStaffById(id);
		repository.delete(staff.get());
		return staff.get();
	}
}
