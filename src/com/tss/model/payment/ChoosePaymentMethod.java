package com.tss.model.payment;

import java.util.Scanner;

public class ChoosePaymentMethod {
	static Scanner scanner = new Scanner(System.in);

	public static IPayment selectPaymentMethod() {
		
		IPayment payment = null;
		boolean selectPaymentMethod = true;
		while (selectPaymentMethod) {
			System.out.println("+------------------+-------------------+----------------+------------+");
			System.out.printf("| %-16s | %-17s | %-14s | %-10s |\n", 
			                  "1. Debit Card", "2. Credit Card", "3. UPI", "4. Cash");
			System.out.println("+------------------+-------------------+----------------+------------+");
			System.out.print("Choose Payment Method: ");
			int paymentChoice = scanner.nextInt();
			scanner.nextLine();
			switch (paymentChoice) {
			case 1 -> {
				System.out.print("Enter Card Number (16 digits): ");
				long cardNumber = scanner.nextLong();
				scanner.nextLine();
				System.out.print("Enter Card Holder Name: ");
				String holder = scanner.nextLine();
				System.out.print("Set PIN(4-digit): ");
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
				System.out.print("Set PIN(4-digit): ");
				int pin = scanner.nextInt();
				scanner.nextLine();
				payment = new CreditCard(cardNumber, holder, pin);
			}
			case 3 -> {
				System.out.print("Enter UPI ID: ");
				String upiId = scanner.nextLine();
				System.out.print("Set PIN(4-digit): ");
				int pin = scanner.nextInt();
				scanner.nextLine();
				payment = new UPI(upiId, pin);
			}
			case 4 -> {
				payment = new CashPayment();
			}
			default -> System.out.println("Invalid payment choice.");
			}
			if (payment.validatePaymentDetails())
				return payment;

		}
		return null;
	}

}
