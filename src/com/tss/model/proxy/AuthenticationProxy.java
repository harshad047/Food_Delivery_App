package com.tss.model.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.model.discount.DiscountManager;
import com.tss.model.repositary.Repositary;
import com.tss.model.admin.Admin;
import com.tss.model.customer.Client;
import com.tss.model.customer.Customer;
import com.tss.model.customer.CustomerProfileManager;
import com.tss.model.customer.RegisterCustomer;

public class AuthenticationProxy {

    private static final String ADMIN_USERNAME = "harshad";
    private static final String ADMIN_PASSWORD = "Fggv@676";
    private static final String CUSTOMER_FILE = "customers.ser";

    public static void authenticateAndStart(String role, String username, String password, Scanner scanner) {

        DiscountManager discountManager = new DiscountManager();
        DeliveryPartnerManager deliveryPartnerManager = new DeliveryPartnerManager();

        AuthenticationProxy authProxy = new AuthenticationProxy();

        if (role.equalsIgnoreCase("Admin")) {
            if (authProxy.isAdmin(username, password)) {
                Admin admin = new Admin(discountManager, deliveryPartnerManager);
                admin.manageAdmin(username);
            } else {
                System.out.println("Invalid Admin credentials. Access denied.");
            }
        } else if (role.equalsIgnoreCase("Customer")) {
            Customer existingCustomer = isCustomer(username, password);
            if (existingCustomer != null) {
                CustomerProfileManager.manageCustomerProfile(existingCustomer, scanner);

                List<Customer> customers = Repositary.readFromFile(CUSTOMER_FILE);
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getName().equals(existingCustomer.getName())) {
                        customers.set(i, existingCustomer);
                        break;
                    }
                }
                Repositary.saveToFile(customers, CUSTOMER_FILE);

                Client client = new Client(scanner, discountManager, deliveryPartnerManager, existingCustomer);
                client.start();
            } else {
                System.out.println("No account found.");
                System.out.print("Do you want to create a new account? (yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("no")) {
                    System.out.println("Goodbye!");
                    return;
                }

                Customer newCustomer = RegisterCustomer.registerNewCustomer(scanner);
                if (newCustomer != null) {
                    List<Customer> customers = Repositary.readFromFile(CUSTOMER_FILE);
                    if (customers == null) {
                        customers = new ArrayList<>();
                    }
                    customers.add(newCustomer);
                    Repositary.saveToFile(customers, CUSTOMER_FILE);

                    Client client = new Client(scanner, discountManager, deliveryPartnerManager, newCustomer);
                    client.start();
                } else {
                    System.out.println("Registration failed. Exiting...");
                }
            }
        } else {
            System.out.println("Invalid role specified. Exiting...");
        }
    }

    private boolean isAdmin(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    private static Customer isCustomer(String username, String password) {
        List<Customer> customers = Repositary.readFromFile(CUSTOMER_FILE);
        if (customers != null) {
            for (Customer customer : customers) {
                if (customer.getName().equals(username) && customer.getPassword().equals(password)) {
                    return customer;
                }
            }
        }
        return null;
    }
}