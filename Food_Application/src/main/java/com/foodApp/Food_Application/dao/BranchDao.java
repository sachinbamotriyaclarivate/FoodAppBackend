package com.foodApp.Food_Application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodApp.Food_Application.dto.Admin;
import com.foodApp.Food_Application.dto.Branch;
import com.foodApp.Food_Application.repository.BranchRepository;

@Repository
public class BranchDao {
	@Autowired
	BranchRepository repository;

	
	public Branch saveBranch(Branch branch) {
		return repository.save(branch);
	}
	public Optional<Branch> findBranchById(int id) {
		return repository.findById(id);
	}

	public Branch updateBranch(Branch branch, int id) {
		
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			Admin admin = findBranchById(id).get().getAdmin();
			branch.setAdmin(admin);
			branch.setBranchId(id);
			return repository.save(branch);
		}
	}
	public Branch deleteBranch(int id) {
		Optional<Branch> branch = findBranchById(id);
		
		repository.delete(branch.get());
		return branch.get();
	}

	public List<Branch> findAllBranch() {
		return repository.findAll();
	}

}
