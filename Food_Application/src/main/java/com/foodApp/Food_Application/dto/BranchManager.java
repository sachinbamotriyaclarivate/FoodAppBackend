package com.foodApp.Food_Application.dto;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BranchManager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branchManagerId;
	private String name;
	private String email;
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="branch_id")
	private Branch branch;

	public int getBranchManagerId() {
		return branchManagerId;
	}
	public void setBranchManagerId(int branchManagerId) {
		this.branchManagerId = branchManagerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}





}
