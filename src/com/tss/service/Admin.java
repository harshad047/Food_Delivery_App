package com.tss.service;

import java.util.Scanner;

import com.tss.model.discount.DiscountManager;
import com.tss.model.food.FoodCusine;
import com.tss.model.food.FoodMenuFactory;
import com.tss.model.discount.IDiscountManager;
import com.tss.model.exception.ItemAlreadyExistsException;
import com.tss.model.exception.NoItemInListException;
import com.tss.model.exception.NoSuchItemFoundException;
import com.tss.model.food.IMenu;
import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.model.menuPrinter.MenuPrinter;

public class Admin {

	private final Scanner scanner;
	private final FoodMenuFactory foodMenuFactory;
	private final IDiscountManager discountManager;
	private final DeliveryPartnerManager deliveryPartnerManager;

	public Admin(Scanner scanner, FoodMenuFactory foodMenuFactory, IDiscountManager discountManager,
			DeliveryPartnerManager deliveryPartnerManager) {
		this.scanner = scanner;
		this.foodMenuFactory = foodMenuFactory;
		this.discountManager = discountManager;
		this.deliveryPartnerManager = deliveryPartnerManager;
	}

	public void manageAdmin(String userName) {
		boolean adminExit = false;
		
		System.out.println("\n+----------------------------------------+");
		System.out.printf ("|  Welcome %s! You are Admin!!      |\n", userName);
		System.out.println("+----------------------------------------+");

		while (!adminExit) {
			System.out.println("\n+-----------------------------+");
			System.out.println("|         Admin Menu          |");
			System.out.println("+-----------------------------+");
			System.out.println("| 1. Manage Food Menu         |");
			System.out.println("| 2. Manage Discount          |");
			System.out.println("| 3. Manage Delivery Partners |");
			System.out.println("| 4. Exit                     |");
			System.out.println("+-----------------------------+");
			System.out.print("Choose: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1 -> manageMenu();
			case 2 -> manageDiscount();
			case 3 -> manageDeliveryPartners();
			case 4 -> adminExit = true;
			default -> System.out.println("Invalid choice!");
			}
		}
	}

	private void manageMenu() {
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
			System.out.println("Invalid cuisine.");
			return;
		}

		IMenu menu = foodMenuFactory.chooseCousine(selected);

		boolean menuExit = false;
		while (!menuExit) {
			System.out.println("\n+-----------------------------+");
			System.out.println("|       Admin Food Menu       |");
			System.out.println("+-----------------------------+");
			System.out.printf("| 1. Add Item                 |\n");
			System.out.printf("| 2. Remove Item              |\n");
			System.out.printf("| 3. Edit Item                |\n");
			System.out.printf("| 4. View Menu                |\n");
			System.out.printf("| 5. Back                     |\n");
			System.out.println("+-----------------------------+");

			
			System.out.print("Choose: ");
			int menuChoice = scanner.nextInt();
			scanner.nextLine();
			switch (menuChoice) {
			case 1 -> {
				try {
					menu.addItem();
				} catch (ItemAlreadyExistsException e) {
					System.out.println(e.getMessage());
				}
			}
			case 2 -> {
				try {
					
					MenuPrinter.printMenu(menu);
					menu.removeItem();
				} catch (NoItemInListException | NoSuchItemFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			case 3 -> {
				try {
					MenuPrinter.printMenu(menu);
					System.out.print("Enter ID to edit: ");
					menu.editItem(scanner.nextInt());
					scanner.nextLine();
				} catch (NoItemInListException | NoSuchItemFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			case 4 -> MenuPrinter.printMenu(menu);
			case 5 -> menuExit = true;
			default -> System.out.println("Invalid choice.");
			}
		}
	}

	private void manageDiscount() {
		boolean back = false;
		while (!back) {
			System.out.println("\n+-----------------------------+");
			System.out.println("|       Discount Menu         |");
			System.out.println("+-----------------------------+");
			System.out.printf("| 1. Add/Edit Discount        |\n");
			System.out.printf("| 2. Remove Discount          |\n");
			System.out.printf("| 3. View Discount            |\n");
			System.out.printf("| 4. Back                     |\n");
			System.out.println("+-----------------------------+");
			System.out.print("Choose: ");

			int choice1 = scanner.nextInt();
			scanner.nextLine();
			switch (choice1) {
			case 1 -> {
				System.out.print("Enter new Discount %: ");
				double percent = scanner.nextDouble();
				scanner.nextLine();
				((DiscountManager) discountManager).getRegularDiscount().setDiscountPercentage(percent);
				System.out.println("Discount saved.");
			}
			case 2 -> {
				((DiscountManager) discountManager).getRegularDiscount().setDiscountPercentage(0.0);
				System.out.println("Discount cleared (set to 0%).");
			}
			case 3 -> System.out.println("Regular Discount: "
					+ ((DiscountManager) discountManager).getRegularDiscount().getDiscountPercentage() + "%");
			case 4 -> back = true;
			default -> System.out.println("Invalid choice.");
			}
		}
	}

	private void manageDeliveryPartners() {
		boolean back = false;
		while (!back) {
			System.out.println("\n+--------------------------------------+");
			System.out.println("|        Delivery Partner Menu         |");
			System.out.println("+--------------------------------------+");
			System.out.printf("| 1. Add Delivery Partner              |\n");
			System.out.printf("| 2. Remove Delivery Partner           |\n");
			System.out.printf("| 3. View Delivery Partners            |\n");
			System.out.printf("| 4. Back                              |\n");
			System.out.println("+--------------------------------------+");
			System.out.print("Choose: ");

			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1 -> {
				System.out.print("Enter new Partner Name: ");
				String name = scanner.nextLine();
				deliveryPartnerManager.addPartner(name);
				System.out.println("Partner added.");
			}
			case 2 -> {
				System.out.println("Current Partners:");
				String[] partners = deliveryPartnerManager.getPartners();
				for (int i = 0; i < partners.length; i++) {
					System.out.println(i + ": " + partners[i]);
				}
				System.out.print("Enter index to remove: ");
				int idx = scanner.nextInt();
				scanner.nextLine();
				deliveryPartnerManager.removePartner(idx);
				System.out.println("Partner removed.");
			}
			case 3 -> {
				System.out.println("Delivery Partners:");
				String[] partners = deliveryPartnerManager.getPartners();
				for (String p : partners) {
					System.out.println(p);
				}
			}
			case 4 -> back = true;
			default -> System.out.println("Invalid choice.");
			}
		}
		return;
	}
}
