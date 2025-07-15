package com.tss.service;

import java.util.Scanner;

import com.tss.model.payment.CashPayment;
import com.tss.model.payment.CreditCard;
import com.tss.model.payment.DebitCard;
import com.tss.model.payment.IPayment;
import com.tss.model.payment.UPI;

public class CustomerProfileManager {

    private static final String CUSTOMER_FILE = "customers.ser";

    public static void manageCustomerProfile(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("\nCustomer Profile Menu:");
            System.out.println("1. Change Address");
            System.out.println("2. Change Payment Method");
            System.out.println("3. Continue to Main Menu");

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
                	System.out.println("Select Payment Method: 1. Debit Card 2. Credit Card 3. UPI 4. Cash");
                    int paymentChoice = scanner.nextInt();
                    scanner.nextLine();

                    IPayment payment = null;

                    switch (paymentChoice) {
                        case 1 -> {
                            System.out.print("Enter Card Number (16 digits): ");
                            long cardNumber = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Enter Card Holder Name: ");
                            String holder = scanner.nextLine();
                            System.out.print("Enter 4-digit PIN: ");
                            int pin = scanner.nextInt();
                            scanner.nextLine();
                            payment = new DebitCard(cardNumber, holder, pin);
                        }
                        case 2 -> {
                            System.out.print("Enter Card Number (16 digits): ");
                            long cardNumber = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Enter Card Holder Name: ");
                            String holder = scanner.nextLine();
                            System.out.print("Enter 4-digit PIN: ");
                            int pin = scanner.nextInt();
                            scanner.nextLine();
                            payment = new CreditCard(cardNumber, holder, pin);
                        }
                        case 3 -> {
                            System.out.print("Enter UPI ID: ");
                            String upiId = scanner.nextLine();
                            System.out.print("Enter 4-digit PIN: ");
                            int pin = scanner.nextInt();
                            scanner.nextLine();
                            payment = new UPI(upiId, pin);
                        }
                        case 4 -> {
                        	payment = new CashPayment();
                        }
                        default -> System.out.println("Invalid payment choice.");
                    }
                    customer.setPaymentMethod(payment);
                    break;

                case "3":
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
