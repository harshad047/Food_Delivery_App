package com.tss.model.admin;

import java.util.Scanner;

import com.tss.model.deliverypartner.DeliveryPartnerManager;

public class DeliveryPartner {
	Scanner scanner = new Scanner(System.in);
	private DeliveryPartnerManager deliveryPartner;

	public DeliveryPartner(DeliveryPartnerManager deliveryPartnerManager) {
		this.deliveryPartner = deliveryPartnerManager;
	}
	public void manageDeliveryPartners() {
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
				deliveryPartner.addPartner(name);
			}
			case 2 -> {
				System.out.println("Current Partners:");
				String[] partners = deliveryPartner.getPartners();
				for (int i = 0; i < partners.length; i++) {
					System.out.println(i + ": " + partners[i]);
				}
				System.out.print("Enter index to remove: ");
				int idx = scanner.nextInt();
				scanner.nextLine();
				deliveryPartner.removePartner(idx);
				System.out.println("Partner removed.");
			}
			case 3 -> {
				System.out.println("Delivery Partners:");
				String[] partners = deliveryPartner.getPartners();
				for (String p : partners) {
				    int len = p.length();
				    String horizontal = "+" + "-".repeat(len + 2) + "+";
				    String line = "| " + p + " |";

				    System.out.println(horizontal);
				    System.out.println(line);
				    System.out.println(horizontal);
				}

			}
			case 4 -> back = true;
			default -> System.out.println("Invalid choice.");
			}
		}
		return;
	}
}
