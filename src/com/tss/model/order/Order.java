package com.tss.model.order;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private final List<OrderItem> items = new ArrayList<>();

	public void addItem(OrderItem item) {
		items.add(item);
	}

	public boolean removeItem(int id) {

		for (int i = 0; i < items.size(); i++) {
			OrderItem item = items.get(i);
			if (item.getId() == id) {
				items.remove(i);
				return true;
			}
		}
		return false;

	}

	public double getTotal() {
		double total = 0;
		for (OrderItem item : items) {
			total += item.getSubtotal();
		}
		return total;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
}
