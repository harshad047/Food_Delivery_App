package com.tss.service;

import java.util.Scanner;

import com.tss.model.food.FoodCusine;
import com.tss.model.food.FoodItem;
import com.tss.model.food.FoodMenu;
import com.tss.model.food.FoodMenuFactory;
import com.tss.model.discount.IDiscountManager;
import com.tss.model.food.IMenu;
import com.tss.model.deliverypartner.DeliveryPartnerManager;
import com.tss.model.menuPrinter.MenuPrinter;

public class Client {

    private final Scanner scanner;
    private final FoodMenuFactory foodMenuFactory;
    private final IDiscountManager discountManager;
    private final DeliveryPartnerManager deliveryPartnerManager;
    private final Customer customer;

    public Client(
        Scanner scanner,
        FoodMenuFactory foodMenuFactory,
        IDiscountManager discountManager,
        DeliveryPartnerManager deliveryPartnerManager,
        Customer customer
    ) {
        this.scanner = scanner;
        this.foodMenuFactory = foodMenuFactory;
        this.discountManager = discountManager;
        this.deliveryPartnerManager = deliveryPartnerManager;
        this.customer = customer;
    }

    public void start() {
        System.out.println("\nWelcome " + customer.getName() + "!");

        System.out.println("Select Cuisine: 1. Italian 2. Indian 3. Korean");
        int cuisineChoice = scanner.nextInt();
        scanner.nextLine();
        FoodCusine selected = switch (cuisineChoice) {
            case 1 -> FoodCusine.ItalianMenu;
            case 2 -> FoodCusine.IndianMenu;
            case 3 -> FoodCusine.KoreanMenu;
            default -> null;
        };

        if (selected == null) {
            System.out.println("Invalid cuisine. Exiting...");
            return;
        }

        IMenu menu = foodMenuFactory.chooseCousine(selected);

        if (menu instanceof FoodMenu fm) {
            if (fm.getMenuItems().isEmpty()) {
                System.out.println("Menu is empty. Please contact Admin.");
                return;
            }

            double total = 0;
            boolean ordering = true;
            StringBuilder invoice = new StringBuilder();
            invoice.append("\n------ INVOICE ------\n");
            invoice.append(String.format("%-20s %-10s %-10s %-10s%n", "Item", "Qty", "Price", "Subtotal"));

            while (ordering) {
                MenuPrinter.printMenu(menu);

                System.out.print("Enter Item ID to add (or 0 to finish): ");
                int itemId = scanner.nextInt();
                if (itemId == 0) break;

                System.out.print("Enter Quantity: ");
                int qty = scanner.nextInt();

                boolean found = false;
                for (FoodItem item : fm.getMenuItems()) {
                    if (item.getId() == itemId) {
                        double sub = item.getPrice() * qty;
                        total += sub;
                        invoice.append(String.format("%-20s %-10d %-10.2f %-10.2f%n",
                                item.getFoodItemName(), qty, item.getPrice(), sub));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Invalid item ID.");
                }
            }

            if (total == 0) {
                System.out.println("No items ordered. Exiting...");
                return;
            }

            double discountedTotal = discountManager.applyDiscounts(total);

            System.out.println("\nOriginal Total: ₹" + total);
            System.out.println("Discounted Total: ₹" + discountedTotal);

            System.out.println("\nProcessing payment...");
            customer.getPaymentMethod().processPayment(discountedTotal);

            String assignedPartner = deliveryPartnerManager.assignPartner();

            invoice.append("\n----------------------------\n");
            invoice.append(String.format("Original Total: ₹%.2f%n", total));
            invoice.append(String.format("Discounted Total: ₹%.2f%n", discountedTotal));
            invoice.append(String.format("Payment Mode: %s%n", customer.getPaymentMethod().getClass().getSimpleName()));
            invoice.append(String.format("Delivery Partner: %s%n", assignedPartner));
            invoice.append("----------------------------\n");

            System.out.println(invoice);
            System.out.println("Thank you for ordering with us, " + customer.getName() + "!");
        }
    }
}
