package com.tss.model.customer;

import java.io.Serializable;
import java.util.Scanner;


public class RegisterCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	public static Customer registerNewCustomer(Scanner scanner) {
		System.out.println("+----------------------------------+");
		System.out.println("|      Register New Customer       |");
		System.out.println("+----------------------------------+");

		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		System.out.print("Enter your address: ");
		String address = scanner.nextLine();
		System.out.print("Set your password: ");
		String password = scanner.nextLine();
		Customer newCustomer = new Customer(name, address, password);

		System.out.println("+----------------------------------+");
		System.out.println("|   Account created successfully!  |");
		System.out.println("+----------------------------------+");

		return newCustomer;
	}

}
