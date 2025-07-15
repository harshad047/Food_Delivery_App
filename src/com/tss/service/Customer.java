package com.tss.service;

import com.tss.model.payment.IPayment;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String address;
	private String password;

	public Customer(String name, String address, String password) {
		this.name = name;
		this.address = address;
		this.password = password;
	}

	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Customer Name: " + name + ", Address: " + address;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
