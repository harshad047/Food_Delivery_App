package com.tss.model.Printer;

import java.util.List;

import com.tss.model.order.Order;
import com.tss.model.order.OrderItem;

public class invoicePrinter {
	public static void printInvoice(double discountedTotal, String paymentMode, String deliveryPartner,List<OrderItem> items,Order order) {
	    String line = "+-----------------------------------------------+";
	    System.out.println(line);
	    System.out.printf("| %-45s |\n", "INVOICE");
	    System.out.println(line);
	    System.out.printf("| %-15s : %-25s |\n", "Date", java.time.LocalDate.now());
	    System.out.printf("| %-15s : %-25s |\n", "Time", java.time.LocalTime.now());
	    System.out.printf("| %-15s : %-25s |\n", "Payment Mode", paymentMode);
	    System.out.printf("| %-15s : %-25s |\n", "Delivery", deliveryPartner);
	    System.out.println(line);
	    System.out.printf("| %-20s %-5s %-8s %-10s |\n", "Item", "Qty", "Price", "Subtotal");
	    System.out.println(line);

	    for (OrderItem item : items) {
	        System.out.printf("| %-20s %-5d ₹%-7.2f ₹%-9.2f |\n",
	                item.getName(), item.getQuantity(), item.getPrice(), item.getSubtotal());
	    }

	    System.out.println(line);
	    System.out.printf("| %-32s ₹%-9.2f |\n", "Original Total", order.getTotal());
	    System.out.printf("| %-32s ₹%-9.2f |\n", "Discounted Total", discountedTotal);
	    System.out.println(line);
	}


}
