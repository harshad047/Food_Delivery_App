package com.tss.model.customer;

import java.util.Scanner;

import com.tss.model.Printer.MenuPrinter;
import com.tss.model.Printer.OrderPrinter;
import com.tss.model.Printer.InvoicePrinter;
import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.model.discount.IDiscountManager;
import com.tss.model.exception.InSufficientQuantityException;
import com.tss.model.exception.NoItemInListException;
import com.tss.model.exception.NoSuchItemFoundException;
import com.tss.model.food.ChooseCousine;
import com.tss.model.food.FoodItem;
import com.tss.model.food.FoodMenu;
import com.tss.model.food.IMenu;
import com.tss.model.order.Order;
import com.tss.model.order.OrderItem;
import com.tss.model.payment.ChoosePaymentMethod;
import com.tss.model.payment.IPayment;

public class Client {

	private final Scanner scanner;
	private final IDiscountManager discountManager;
	private final DeliveryPartnerManager deliveryPartnerManager;
	private final Customer customer;

	public Client(Scanner scanner, IDiscountManager discountManager, DeliveryPartnerManager deliveryPartnerManager,
			Customer customer) {
		this.scanner = scanner;
		this.discountManager = discountManager;
		this.deliveryPartnerManager = deliveryPartnerManager;
		this.customer = customer;
	}

	public void start() {
		System.out.println("\nWelcome " + customer.getName() + "!");
		Order order = new Order();

		boolean ordering = true;

		while (ordering) {
			IMenu menu = ChooseCousine.choose();

			if (menu instanceof FoodMenu fm) {
				if (fm.getMenuItems().isEmpty()) {
					System.out.println("Menu is empty.");
					continue;
				}

				boolean inCuisine = true;
				while (inCuisine) {

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
						System.out.println("\n+------------------------+");
						System.out.printf("| %-23s|\n", menu.getClass().getSimpleName());
						System.out.println("+------------------------+");
						MenuPrinter.printMenu(menu);
						System.out.print("Enter Item ID to add: ");
						String addId = scanner.nextLine();
						System.out.print("Quantity: ");
						int qty = scanner.nextInt();
						scanner.nextLine();

						if(qty==0)
						{
							System.out.println("Quantity Can't be Zero !!");
							break;
						}
						boolean found = false;
						for (FoodItem item : fm.getMenuItems()) {
							if (item.getId().equalsIgnoreCase(addId)) {
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
						try {
							OrderPrinter.orderPrinter(order);
							System.out.print("Enter Item ID to remove: ");
							String removeId = scanner.nextLine();
							System.out.println("Enter Quantity: ");
							int qty = scanner.nextInt();
							scanner.nextLine();
							if (order.removeItem(removeId, qty)) {
								System.out.println("Removed. Item With " + removeId);
							}

						} catch (NoItemInListException | NoSuchItemFoundException | InSufficientQuantityException e) {
							System.out.println(e.getMessage());
						}
					}

					case 3 -> {
						OrderPrinter.orderPrinter(order);
					}
					case 4 -> {
						try {
							double total = order.getTotal();
							double discountedTotal = discountManager.applyDiscounts(total);

							System.out.printf("\nOriginal Total: ₹%.2f%n", total);
							System.out.printf("Discounted Total: ₹%.2f%n", discountedTotal);
							String assignedPartner = deliveryPartnerManager.assignPartner();

							boolean payment = true;
							while (payment) {
								IPayment paymentProcess = ChoosePaymentMethod.selectPaymentMethod();
								if (paymentProcess.processPayment(discountedTotal)) {
									InvoicePrinter.printInvoice(discountedTotal,
											paymentProcess.getClass().getSimpleName(), assignedPartner,
											order.getItems(), order);

									System.out.println("Thank you for ordering with us!");
									payment = false;
									ordering = false;
									inCuisine = false;
								} else {
									System.out.println("Payment Failed !! Try Another Method");
								}
							}
						} catch (NoItemInListException e) {
							System.out.println(e.getMessage());
						}
					}
					case 5 -> inCuisine = false;
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
