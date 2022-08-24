package com.foodApp.Food_Application.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
	private int id;
	private long totalAmount;
	private String status;
	private String orderCreatedTime;
	private String orderDeliverdTime;
	private int quantity;
	private String customerName;
	private String customerEmail;
	private String customerPhoneNumber;

	@ManyToOne()
	@JoinColumn(name="staff_id")
	private Staff staff;

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Items> item;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderCreatedTime() {
		return orderCreatedTime;
	}
	public void setOrderCreatedTime(String orderCreatedTime) {
		this.orderCreatedTime = orderCreatedTime;
	}
	public String getOrderDeliverdTime() {
		return orderDeliverdTime;
	}
	public void setOrderDeliverdTime(String orderDeliverdTime) {
		this.orderDeliverdTime = orderDeliverdTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public List<Items> getItem() {
		return item;
	}
	public void setItem(List<Items> item) {
		this.item = item;
	}
	



}
