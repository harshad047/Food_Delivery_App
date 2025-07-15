package com.tss.service;

import java.io.Serializable;
import java.util.Scanner;

import com.tss.model.payment.CreditCard;
import com.tss.model.payment.DebitCard;
import com.tss.model.payment.IPayment;
import com.tss.model.payment.UPI;
import com.tss.model.repositary.Repositary;

public class RegisterCustomer implements Serializable{
    private static final long serialVersionUID = 1L;

    public static Customer registerNewCustomer(Scanner scanner) {
        System.out.println("=== Register New Customer ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Set your password: ");
        String password = scanner.nextLine();

        System.out.println("Select Payment Method: 1. Debit Card 2. Credit Card 3. UPI");
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
            default -> System.out.println("Invalid payment choice.");
        }

        if (payment == null || !payment.validatePaymentDetails()) {
            System.out.println("Invalid payment details. Registration failed.");
            return null;
        }

        Customer newCustomer = new Customer(name, address, password, payment);
        System.out.println("Account created successfully! Your ID: " + newCustomer.getId());
        return newCustomer;
    }

}
