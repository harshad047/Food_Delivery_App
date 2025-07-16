package com.tss.model.admin;

import java.util.Scanner;

import com.tss.model.discount.DiscountManager;

public class DiscountManagerAdmin {

	Scanner scanner = new Scanner(System.in);
	private DiscountManager discountManager;

	public DiscountManagerAdmin(DiscountManager discountManager) {
		super();
		this.discountManager = discountManager;
	}

	void manageDiscount() {
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
				discountManager.getRegularDiscount().setDiscountPercentage(percent);
				System.out.println("Discount saved.");
			}
			case 2 -> {
				discountManager.getRegularDiscount().setDiscountPercentage(0.0);
				System.out.println("Discount cleared (set to 0%).");
			}
			case 3 -> System.out.println("Regular Discount: "
					+ discountManager.getRegularDiscount().getDiscountPercentage() + "%");
			case 4 -> back = true;
			default -> System.out.println("Invalid choice.");
			}
		}
	}
}
