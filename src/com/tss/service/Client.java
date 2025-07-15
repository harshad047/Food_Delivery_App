package com.tss.service;

import java.util.Scanner;

import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.model.discount.IDiscountManager;
import com.tss.model.food.*;
import com.tss.model.menuPrinter.MenuPrinter;
import com.tss.model.order.Order;
import com.tss.model.order.OrderItem;

public class Client {

	private final Scanner scanner;
	private final FoodMenuFactory foodMenuFactory;
	private final IDiscountManager discountManager;
	private final DeliveryPartnerManager deliveryPartnerManager;
	private final Customer customer;

	public Client(Scanner scanner, FoodMenuFactory foodMenuFactory, IDiscountManager discountManager,
			DeliveryPartnerManager deliveryPartnerManager, Customer customer) {
		this.scanner = scanner;
		this.foodMenuFactory = foodMenuFactory;
		this.discountManager = discountManager;
		this.deliveryPartnerManager = deliveryPartnerManager;
		this.customer = customer;
	}

	public void start() {
		System.out.println("\nWelcome " + customer.getName() + "!");
		Order order = new Order();

		boolean ordering = true;

		while (ordering) {
			System.out.println("\nSelect Cuisine: 1. Italian 2. Indian 3. Korean 0. Exit");
			int cuisineChoice = scanner.nextInt();
			scanner.nextLine();

			if (cuisineChoice == 0) {
				System.out.println("Goodbye!");
				return;
			}

			FoodCusine selected = switch (cuisineChoice) {
			case 1 -> FoodCusine.ItalianMenu;
			case 2 -> FoodCusine.IndianMenu;
			case 3 -> FoodCusine.KoreanMenu;
			default -> null;
			};

			if (selected == null) {
				System.out.println("Invalid cuisine. Try again.");
				continue;
			}

			IMenu menu = foodMenuFactory.chooseCousine(selected);

			if (menu instanceof FoodMenu fm) {
				if (fm.getMenuItems().isEmpty()) {
					System.out.println("Menu is empty.");
					continue;
				}

				boolean inCuisine = true;
				while (inCuisine) {
					System.out.println("\n---- " + selected + " ----");
					MenuPrinter.printMenu(menu);

					System.out.println("""
							Options:
							1. Add Item
							2. Remove Item
							3. View Current Order
							4. Checkout
							5. Back to Cuisine
							""");

					System.out.print("Choose option: ");
					int choice = scanner.nextInt();
					scanner.nextLine();

					switch (choice) {
					case 1 -> {
						System.out.print("Enter Item ID to add: ");
						int addId = scanner.nextInt();
						System.out.print("Quantity: ");
						int qty = scanner.nextInt();
						scanner.nextLine();

						boolean found = false;
						for (FoodItem item : fm.getMenuItems()) {
							if (item.getId() == addId) {
								order.addItem(
										new OrderItem(item.getId(), item.getFoodItemName(), item.getPrice(), qty));
								System.out.println("Added: " + item.getFoodItemName());
								found = true;
								break;
							}
						}
						if (!found) {
							System.out.println("Invalid item ID.");
						}
					}
					case 2 -> {
						System.out.print("Enter Item ID to remove: ");
						int removeId = scanner.nextInt();
						scanner.nextLine();
						boolean removed = order.removeItem(removeId);
						System.out.println(removed ? "Removed." : "Item not found.");
					}
					case 3 -> {
						if (order.isEmpty()) {
							System.out.println("Order is empty.");
						} else {
							System.out.println("\nCurrent Order:");
							for (OrderItem item : order.getItems()) {
								System.out.printf("%-20s Qty: %d Price: %.2f Subtotal: %.2f%n", item.getName(),
										item.getQuantity(), item.getPrice(), item.getSubtotal());
							}
							System.out.printf("Current Total: ₹%.2f%n", order.getTotal());
						}
					}
					case 4 -> {
						if (order.isEmpty()) {
							System.out.println("Order is empty. Cannot checkout.");
						} else {
							double total = order.getTotal();
							double discountedTotal = discountManager.applyDiscounts(total);

							System.out.printf("\nOriginal Total: ₹%.2f%n", total);
							System.out.printf("Discounted Total: ₹%.2f%n", discountedTotal);

							customer.getPaymentMethod().processPayment(discountedTotal);

							String assignedPartner = deliveryPartnerManager.assignPartner();

							order.printInvoice(discountedTotal, customer.getPaymentMethod().getClass().getSimpleName(),
									assignedPartner);

							System.out.println("Thank you for ordering with us!");
							return;
						}
					}
					case 5 -> {
						inCuisine = false; 
					}
					default -> System.out.println("Invalid option.");
					}
				}
			}
		}
	}
}
