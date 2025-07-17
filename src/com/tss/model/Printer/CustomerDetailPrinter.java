package com.tss.model.Printer;

import com.tss.model.customer.Customer;

public class CustomerDetailPrinter {
	public static void printInfo(Customer customer)
	{
		String name = customer.getName();
		String address = customer.getAddress();
		String password = customer.getPassword();

		if (address.length() > 40) {
		    address = address.substring(0, 37) + "...";
		}

		System.out.println("\n+---------------------------------------------+");
		System.out.println("|              Customer Details               |");
		System.out.println("+---------------------------------------------+");
		System.out.printf("| Name     : %-32s |\n", name);
		System.out.printf("| Address  : %-32s |\n", address);
		System.out.printf("| Password : %-32s |\n", password);
		System.out.println("+---------------------------------------------+");

		
	}
}
