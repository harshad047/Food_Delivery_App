package com.tss.model.customer;

import java.util.Scanner;

import com.tss.model.Printer.MenuPrinter;
import com.tss.model.Printer.invoicePrinter;
import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.model.discount.IDiscountManager;
import com.tss.model.food.FoodCusine;
import com.tss.model.food.FoodItem;
import com.tss.model.food.FoodMenu;
import com.tss.model.food.FoodMenuFactory;
import com.tss.model.food.IMenu;
import com.tss.model.order.Order;
import com.tss.model.order.OrderItem;
import com.tss.model.payment.ChoosePaymentMethod;
import com.tss.model.payment.IPayment;

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
			System.out.println("+-----------------------------------------+");
			System.out.println("|           Select Cuisine                |");
			System.out.println("+-----------------------------------------+");
			System.out.printf("| 1. Italian                              |\n");
			System.out.printf("| 2. Indian                               |\n");
			System.out.printf("| 3. Korean                               |\n");
			System.out.println("+-----------------------------------------+");

			System.out.print("Choose: ");
			int cuisineChoice = scanner.nextInt();
			scanner.nextLine();

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
					System.out.println("\n+------------------------+");
					System.out.printf("| %-23s|\n", selected);
					System.out.println("+------------------------+");
					MenuPrinter.printMenu(menu);

					System.out.println("+--------------------------+");
					System.out.println("|         Options          |");
					System.out.println("+--------------------------+");
					System.out.println("| 1. Order Item            |");
					System.out.println("| 2. Remove Item           |");
					System.out.println("| 3. View Current Order    |");
					System.out.println("| 4. Checkout              |");
					System.out.println("| 5. Back to Cuisine       |");
					System.out.println("| 6. Exit                  |");
					System.out.println("+--------------------------+");

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
							System.out.println("Order is empty.!!");
						} else {
							double total = order.getTotal();
							double discountedTotal = discountManager.applyDiscounts(total);

							System.out.printf("\nOriginal Total: ₹%.2f%n", total);
							System.out.printf("Discounted Total: ₹%.2f%n", discountedTotal);
							String assignedPartner = deliveryPartnerManager.assignPartner();
							boolean payment = true;
							while (payment) {
								IPayment paymentProcess = ChoosePaymentMethod.selectPaymentMethod();
								if (paymentProcess.processPayment(discountedTotal)) {
									invoicePrinter.printInvoice(discountedTotal,
											paymentProcess.getClass().getSimpleName(), assignedPartner,
											order.getItems(), order);

									System.out.println("Thank you for ordering with us!");
									payment = false;
									return;
								}
								System.out.println("Payment Failed !! Try Another Method");
							}
						}
					}
					case 5 -> {
						inCuisine = false;
					}
					case 6 -> {
						System.out.println("Exiting !!");
						return;
					}
					default -> System.out.println("Invalid option.");
					}
				}
			}
		}
	}
}
