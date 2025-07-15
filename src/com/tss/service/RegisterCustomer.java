package com.tss.service;

import java.io.Serializable;
import java.util.Scanner;

import com.tss.model.payment.CreditCard;
import com.tss.model.payment.DebitCard;
import com.tss.model.payment.IPayment;
import com.tss.model.payment.UPI;

public class RegisterCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	public static Customer registerNewCustomer(Scanner scanner) {
		System.out.println("=== Register New Customer ===");
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		System.out.print("Enter your address: ");
		String address = scanner.nextLine();
		System.out.print("Set your password: ");
		String password = scanner.nextLine();

		Customer newCustomer = new Customer(name, address, password);
		System.out.println("Account created successfully!");
		return newCustomer;
	}

}
