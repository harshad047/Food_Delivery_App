package com.tss.model.food;
import java.util.List;

public interface IMenu {
	void addItem();
	void removeItem(int id);
	void editItem(int id);
	 List<FoodItem> getMenuItems();
}
