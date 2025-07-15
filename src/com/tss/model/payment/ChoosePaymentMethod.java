package com.tss.model.payment;

import java.util.Scanner;

public class ChoosePaymentMethod {
	static Scanner scanner = new Scanner(System.in);
	public static IPayment selectPaymentMethod()
	{
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
	    payment.validatePaymentDetails();
	    return payment;
	   

	}

}
