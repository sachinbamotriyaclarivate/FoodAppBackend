package com.foodApp.Food_Application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodApp.Food_Application.dto.Branch;
import com.foodApp.Food_Application.dto.BranchManager;
import com.foodApp.Food_Application.repository.BranchManagerRepository;
import com.foodApp.Food_Application.service.BranchService;
@Repository
public class BranchManagerDao {
	@Autowired
	BranchManagerRepository repository;
	
	public BranchManager saveBranchManager(BranchManager branchManager) {
		return repository.save(branchManager);
	}
	public Optional<BranchManager> findBranchManagerById(int id) {
		return repository.findById(id);
	}
	
	public BranchManager updateBranchManager(BranchManager branchManager, int id) {
		
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			Branch branch =findBranchManagerById(id).get().getBranch();
			branchManager.setBranch(branch);
			
			branchManager.setBranchManagerId(id);
			return repository.save(branchManager);
		}
	}
	public BranchManager deleteBranchManager(int id) {
		Optional<BranchManager> branchManager = findBranchManagerById(id);
		repository.delete(branchManager.get());
		return branchManager.get();
	}


	public List<BranchManager> findAllBranchManager() {
		return repository.findAll();
	}

}
