package com.tss.model.admin;

import java.util.Scanner;

import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.model.discount.DiscountManager;
import com.tss.model.food.FoodMenuFactory;

public class Admin {

	Scanner scanner = new Scanner(System.in);
	private final FoodMenuManager foodMenuManager;
    private final DiscountManagerAdmin discountManager;
    private final DeliveryPartner deliveryPartner;

    public Admin(FoodMenuFactory foodMenuFactory, DiscountManager discountManager,DeliveryPartnerManager deliveryPartnerManager) {
        this.foodMenuManager = new FoodMenuManager(foodMenuFactory);
        this.discountManager = new DiscountManagerAdmin(discountManager);
        this.deliveryPartner = new DeliveryPartner(deliveryPartnerManager);
    }

    public void manageAdmin(String userName) {
        boolean adminExit = false;

        System.out.println("\n+----------------------------------------+");
        System.out.printf("|  Welcome %s! You are Admin!!           |\n", userName);
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
                case 1 -> foodMenuManager.manageMenu();
                case 2 -> discountManager.manageDiscount();
                case 3 -> deliveryPartner.manageDeliveryPartners();
                case 4 -> adminExit = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
