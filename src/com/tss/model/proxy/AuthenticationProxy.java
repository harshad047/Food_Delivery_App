package com.tss.model.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.model.discount.DiscountManager;
import com.tss.model.discount.IDiscountManager;
import com.tss.model.food.FoodMenuFactory;
import com.tss.model.repositary.Repositary;
import com.tss.service.Admin;
import com.tss.service.Client;
import com.tss.service.Customer;
import com.tss.service.CustomerProfileManager;
import com.tss.service.RegisterCustomer;

public class AuthenticationProxy {

    private static final String ADMIN_USERNAME = "harshad";
    private static final String ADMIN_PASSWORD = "Fggv@676";
    private static final String CUSTOMER_FILE = "customers.ser";

    public static void authenticateAndStart(String username, String password, Scanner scanner) {

        FoodMenuFactory foodMenuFactory = new FoodMenuFactory();
        IDiscountManager discountManager = new DiscountManager();
        DeliveryPartnerManager deliveryPartnerManager = new DeliveryPartnerManager();

        AuthenticationProxy authProxy = new AuthenticationProxy();

        if (authProxy.isAdmin(username, password)) {
            Admin admin = new Admin(scanner, foodMenuFactory, discountManager, deliveryPartnerManager);
            admin.manageAdmin(username);
        } else {
            Customer existingCustomer = isCustomer(username, password);
            if (existingCustomer != null) {
                // NEW: Let the customer update profile
                CustomerProfileManager.manageCustomerProfile(existingCustomer, scanner);

                // Save updated info
                List<Customer> customers = Repositary.readFromFile(CUSTOMER_FILE);
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getName().equals(existingCustomer.getName())) {
                        customers.set(i, existingCustomer);
                        break;
                    }
                }
                Repositary.saveToFile(customers, CUSTOMER_FILE);

                Client client = new Client(scanner, foodMenuFactory, discountManager, deliveryPartnerManager, existingCustomer);
                client.start();
                return;
            }

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

                Client client = new Client(scanner, foodMenuFactory, discountManager, deliveryPartnerManager, newCustomer);
                client.start();
                return;
            }

            System.out.println("Registration failed. Exiting...");
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
