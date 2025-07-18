package com.tss.model.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tss.model.exception.InSufficientQuantityException;
import com.tss.model.exception.NoItemInListException;
import com.tss.model.exception.NoSuchItemFoundException;

public class Order {
	private final List<OrderItem> items = new ArrayList<>();
	private final String orderId;

	// Constructor
	public Order() {
		this.orderId = generateOrderId();
	}

	private String generateOrderId() {
		Random rand = new Random();
		int randomNum = 1000 + rand.nextInt(9000); // range: 1000-9999
		return "OR" + randomNum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void addItem(OrderItem item) {
		items.add(item);
	}

	public boolean removeItem(String id, int quantity) {
		if (items.isEmpty()) {
			throw new NoItemInListException();
		}
		boolean exists = false;
		for (int i = 0; i < items.size(); i++) {
			OrderItem item = items.get(i);
			if (item.getId().equalsIgnoreCase(id)) {
				exists = true;
				if (item.getQuantity() < quantity) {
					continue;
				}
				if (item.getQuantity() == quantity) {
					items.remove(i);
				} else {
					item.setQuantity(item.getQuantity() - quantity);
				}
				return true;
			}
		}
		if (!exists)
			throw new NoSuchItemFoundException();
		throw new InSufficientQuantityException();
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
