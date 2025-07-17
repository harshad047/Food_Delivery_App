package com.tss.model.Printer;

import com.tss.model.exception.NoItemInListException;
import com.tss.model.order.Order;
import com.tss.model.order.OrderItem;

public class OrderPrinter {

	public static void orderPrinter(Order order) {
		try {
		    System.out.println("+-------------------------------------------------------------+");
		    System.out.println("|                        Current Order                        |");
		    System.out.println("+------+----------------------+-------+---------+-------------+");
		    System.out.printf("| %-4s | %-20s | %-5s | %-7s | %-11s |\n", "ID", "Item", "Qty", "Price", "Subtotal");
		    System.out.println("+------+----------------------+-------+---------+-------------+");

		    for (OrderItem item : order.getItems()) {
		        System.out.printf("| %-4s | %-20s | %-5d | ₹%-6.2f | ₹%-10.2f |\n",
		                item.getId(),
		                item.getName(),
		                item.getQuantity(),
		                item.getPrice(),
		                item.getSubtotal());
		    }

		    System.out.println("+------+----------------------+-------+---------+-------------+");
		    System.out.printf("| %-49s ₹%-8.2f |\n", "Current Total:", order.getTotal());
		    System.out.println("+-------------------------------------------------------------+");

		} catch (NoItemInListException e) {
		    System.out.println(e.getMessage());
		}
	}
}

