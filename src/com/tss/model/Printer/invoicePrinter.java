package com.tss.model.Printer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.tss.model.order.Order;
import com.tss.model.order.OrderItem;

public class InvoicePrinter {

	public static void printInvoice(double discountedTotal, String paymentMode, String deliveryPartner,
			List<OrderItem> items, Order order) {
		String border = "+--------------------------------------------------------------------------------+";
		int width = border.length();

		System.out.println(border);
		System.out.printf("|%s|\n", centerText("INVOICE", width - 2));
		System.out.println(border);

		System.out.printf("| %-18s : %-55s   |\n", "Order ID", order.getOrderId());
		System.out.printf("| %-18s : %-55s   |\n", "Date", LocalDate.now());
		System.out.printf("| %-18s : %-55s   |\n", "Time", LocalTime.now().withNano(0));
		System.out.printf("| %-18s : %-55s   |\n", "Payment Mode", paymentMode);
		System.out.printf("| %-18s : %-55s   |\n", "Delivery Partner", deliveryPartner);
		System.out.println(border);

		System.out.printf("| %-7s | %-24s | %-8s | %-12s | %-15s |\n", "ID", "Item", "Qty", "Price", "Subtotal");

		System.out.println(border);

		for (OrderItem item : items) {
			String name = item.getName();
			if (name.length() > 24) {
				name = name.substring(0, 21) + "...";
			}

			System.out.printf("| %-7s | %-24s | %-8d | ₹%-11.2f | ₹%-14.2f |\n", item.getId(), name, item.getQuantity(),
					item.getPrice(), item.getSubtotal());
		}

		System.out.println(border);
		System.out.printf("| %-42s : %-24s₹%-8.2f |\n", "Original Total", "", order.getTotal());
		System.out.printf("| %-42s : %-24s₹%-8.2f |\n", "Discounted Total", "", discountedTotal);
		System.out.println(border);
		System.out.printf("|%s|\n", centerText("Thank you for ordering with us!", width - 2));
		System.out.println(border);
	}

	private static String centerText(String text, int width) {
		int padding = (width - text.length()) / 2;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < padding; i++)
			sb.append(" ");
		sb.append(text);

		while (sb.length() < width)
			sb.append(" ");
		return sb.toString();
	}
}
