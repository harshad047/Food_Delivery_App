package com.tss.model.customer;

import java.util.Scanner;

import com.tss.model.Printer.CustomerDetailPrinter;

public class CustomerProfileManager {


	public static void manageCustomerProfile(Customer customer, Scanner scanner) {
		while (true) {
			System.out.println("\n+--------------------------------------------+");
			System.out.println("|          Customer Profile Menu             |");
			System.out.println("+--------------------------------------------+");
			System.out.printf("| %-42s |\n", "1. Change Delivery Address for Order");
			System.out.printf("| %-42s |\n", "2. Proceed to Order");
			System.out.printf("| %-42s |\n", "3. View Details");
			System.out.printf("| %-42s |\n", "4. Exit");
			System.out.println("+--------------------------------------------+");


			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter new address: ");
				String newAddress = scanner.nextLine().trim();
				if (newAddress.trim().equalsIgnoreCase(customer.getAddress().trim())) {
					System.out.println("Address Is Same No Need To change Address");
					break;
				}
				
				customer.setAddress(newAddress);
				System.out.println("Address updated.");
				break;
			case 2:
				return;
			case 3:
				CustomerDetailPrinter.printInfo(customer);
				break;
			case 4:
				System.out.println("Thank You For Visiting !!");
				System.exit(0);

			default:
				System.out.println("Invalid choice. Try again.");
				break;
			}
		}
	}
}
