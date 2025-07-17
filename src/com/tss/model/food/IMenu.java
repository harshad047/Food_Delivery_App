package com.tss.model.food;
import java.util.List;

public interface IMenu {
	void addItem();
	void removeItem();
	void editItem(String id);
	 List<FoodItem> getMenuItems();
}
