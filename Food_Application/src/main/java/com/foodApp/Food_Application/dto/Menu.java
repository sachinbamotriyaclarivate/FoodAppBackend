package com.foodApp.Food_Application.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int menuId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="branchManagerId")
	BranchManager branchManager;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public BranchManager getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(BranchManager branchManager) {
		this.branchManager = branchManager;
	}

}
