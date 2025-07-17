package com.tss.model.order;

import java.util.ArrayList;
import java.util.List;

import com.tss.model.exception.NoItemInListException;
import com.tss.model.exception.NoSuchItemFoundException;

public class Order {
	private final List<OrderItem> items = new ArrayList<>();

	public void addItem(OrderItem item) {
		items.add(item);
	}

	public boolean removeItem(String id, int quantity) {
		if (items.isEmpty()) {
			throw new NoItemInListException();
		}
		boolean isexists = false;
		for (int i = 0; i < items.size(); i++) {
			OrderItem item = items.get(i);
			if (item.getId().equalsIgnoreCase(id)) {
				isexists=true;
				if (item.getQuantity() < quantity) {
					continue;
				}

				if (item.getQuantity() == quantity) {
					items.remove(i); 
				} else {
					item.setQuantity(item.getQuantity() - quantity); 				}

				return true; 
				}
		}
		if(!isexists)
			throw new NoSuchItemFoundException();
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

	public void isEmpty() {
		if (items.isEmpty())
			throw new NoItemInListException();
	}
}
