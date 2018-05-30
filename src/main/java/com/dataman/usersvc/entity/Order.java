package com.dataman.usersvc.entity;

public class Order {
	
	private int id;
	private java.sql.Timestamp ordertime;
	private String status;
	private String description;
	
	public Order() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(java.sql.Timestamp ordertime) {
		this.ordertime = ordertime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}

