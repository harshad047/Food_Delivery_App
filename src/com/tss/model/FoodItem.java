package com.tss.model;

import java.io.Serializable;

public class FoodItem implements Serializable{
	private int id;
	private String FoodItemName;
	private double price;
	private String description;
	
	public FoodItem(int id, String foodItemName, double price,String description) {
		super();
		this.id = id;
		this.FoodItemName = foodItemName;
		this.price = price;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodItemName() {
		return FoodItemName;
	}
	public void setFoodItemName(String foodItemName) {
		this.FoodItemName = foodItemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
