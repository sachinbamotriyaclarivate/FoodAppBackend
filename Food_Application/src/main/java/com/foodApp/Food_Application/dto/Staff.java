package com.foodApp.Food_Application.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffId;
	private String name;
	private String email;
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="branchManagerId")
	BranchManager branchManager;
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
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
	public BranchManager getBranchManager() {
		return branchManager;
	}
	public void setBranchManager(BranchManager branchManager) {
		this.branchManager = branchManager;
	}


}
