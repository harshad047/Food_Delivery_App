package com.tss.service;

import java.util.Scanner;


public class CustomerProfileManager {

    private static final String CUSTOMER_FILE = "customers.ser";

    public static void manageCustomerProfile(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("\nCustomer Profile Menu:");
            System.out.println("1. Change Delivery Address");
            System.out.println("2. Continue to Main Menu");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine().trim();
                    customer.setAddress(newAddress);
                    System.out.println("Address updated.");
                    break;
                case "2":
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
