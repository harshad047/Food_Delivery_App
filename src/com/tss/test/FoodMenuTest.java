package com.tss.test;

import java.util.Scanner;

import com.tss.model.discount.DiscountManager;
import com.tss.model.food.FoodMenuFactory;
import com.tss.model.discount.IDiscountManager;
import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.service.Admin;
import com.tss.service.Client;

public class FoodMenuTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FoodMenuFactory foodMenuFactory = new FoodMenuFactory();
        IDiscountManager discountManager = new DiscountManager();
        DeliveryPartnerManager deliveryPartnerManager = new DeliveryPartnerManager();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (username.equals("harshad") && password.equals("Fggv@676")) {
            Admin admin = new Admin(scanner, foodMenuFactory, discountManager, deliveryPartnerManager);
            admin.start();
        } else {
            System.out.println("Invalid credentials! Continuing as Customer...\n");
            Client client = new Client(scanner, foodMenuFactory, discountManager, deliveryPartnerManager);
            client.start();
        }

        scanner.close();
    }
}
