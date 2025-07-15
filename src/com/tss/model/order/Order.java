package com.tss.model.order;

import java.util.ArrayList;
import java.util.Iterator;
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

	public void printInvoice(double discountedTotal, String paymentMode, String deliveryPartner) {
		System.out.println("\n------ INVOICE ------");
		System.out.printf("%-20s %-10s %-10s %-10s%n", "Item", "Qty", "Price", "Subtotal");
		for (OrderItem item : items) {
			System.out.printf("%-20s %-10d %-10.2f %-10.2f%n", item.getName(), item.getQuantity(), item.getPrice(),
					item.getSubtotal());
		}
		System.out.println("------------------------------");
		System.out.printf("Original Total: ₹%.2f%n", getTotal());
		System.out.printf("Discounted Total: ₹%.2f%n", discountedTotal);
		System.out.printf("Payment Mode: %s%n", paymentMode);
		System.out.printf("Delivery Partner: %s%n", deliveryPartner);
		System.out.println("------------------------------");
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}
}
