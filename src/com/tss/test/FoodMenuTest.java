package com.tss.test;

import com.tss.exception.ItemAlreadyExistsException;
import com.tss.model.*;

import java.util.Scanner;

public class FoodMenuTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodMenuFactory foodMenuFactory = new FoodMenuFactory();
        IDiscountManager realDiscountManager = new DiscountManager();

        System.out.println("Are you Admin or Customer?");
        String userType = scanner.nextLine().trim().toLowerCase();

        if (userType.equalsIgnoreCase("admin")) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (!username.equals("harshad") || !password.equals("Fggv@676")) {
                System.out.println("Invalid credentials! Exiting...");
                return;
            }

            System.out.println("Welcome Admin!");
            boolean adminExit = false;

            IDiscountManager discountProxy = new DiscountManagerProxy(realDiscountManager, username, password);

            while (!adminExit) {
                System.out.println("\n1. Manage Menu");
                System.out.println("2. Manage Discount");
                System.out.println("3. Exit");
                System.out.print("Choose: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Select Cuisine: 1. Italian 2. Indian 3. Korean");
                        int cuisineChoice = scanner.nextInt();
                        FoodCusine selected = switch (cuisineChoice) {
                            case 1 -> FoodCusine.ItalianMenu;
                            case 2 -> FoodCusine.IndianMenu;
                            case 3 -> FoodCusine.KoreanMenu;
                            default -> null;
                        };
                        if (selected == null) {
                            System.out.println("Invalid cuisine.");
                            break;
                        }
                        IMenu menu = foodMenuFactory.chooseCousine(selected);

                        boolean menuExit = false;
                        while (!menuExit) {
                            System.out.println("\n1. Add Item\n2. Remove Item\n3. Edit Item\n4. View Menu\n5. Back");
                            int menuChoice = scanner.nextInt();
                            scanner.nextLine();
                            switch (menuChoice) {
                            
                                case 1 -> {try{menu.addItem();}catch(ItemAlreadyExistsException e) {System.out.println(e.getMessage());}}
                                case 2 -> {
                                    System.out.print("Enter ID to remove: ");
                                    menu.removeItem(scanner.nextInt());
                                    scanner.nextLine();
                                }
                                case 3 -> {
                                    System.out.print("Enter ID to edit: ");
                                    menu.editItem(scanner.nextInt());
                                    scanner.nextLine();
                                }
                                case 4 -> {
                                    if (menu instanceof FoodMenu fm) {
                                        if (fm.getMenuItems().isEmpty()) {
                                            System.out.println("Menu is empty.");
                                        } else {
                                            for (FoodItem item : fm.getMenuItems()) {
                                                System.out.println(item);
                                            }
                                        }
                                    }
                                }
                                case 5 -> menuExit = true;
                                default -> System.out.println("Invalid choice.");
                            }
                        }
                    }
                    case 2 -> {
                        boolean discountExit = false;
                        while (!discountExit) {
                            System.out.println("\n1. Add/Edit Discount");
                            System.out.println("2. Remove Discount");
                            System.out.println("3. View Discount");
                            System.out.println("4. Back");
                            int dChoice = scanner.nextInt();
                            scanner.nextLine();
                            switch (dChoice) {
                                case 1 -> discountProxy.addDiscount();
                                case 2 -> discountProxy.removeDiscount();
                                case 3 -> discountProxy.viewDiscounts();
                                case 4 -> discountExit = true;
                                default -> System.out.println("Invalid choice.");
                            }
                        }
                    }
                    case 3 -> adminExit = true;
                    default -> System.out.println("Invalid choice!");
                }
            }
        } else if (userType.equalsIgnoreCase("customer")) {
            System.out.println("Welcome Customer!");

            System.out.println("Select Cuisine: 1. Italian 2. Indian 3. Korean");
            int cuisineChoice = scanner.nextInt();
            FoodCusine selected = switch (cuisineChoice) {
                case 1 -> FoodCusine.ItalianMenu;
                case 2 -> FoodCusine.IndianMenu;
                case 3 -> FoodCusine.KoreanMenu;
                default -> null;
            };
            if (selected == null) {
                System.out.println("Invalid cuisine.");
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
                while (ordering) {
                    System.out.println("\n--- Menu ---");
                    System.out.printf("%-5s %-20s %-30s %-10s%n", "ID", "Name", "Description", "Price (₹)");
                    System.out.println("-------------------------------------------------------------------------------");
                    for (FoodItem item : fm.getMenuItems()) {
                        System.out.printf("%-5d %-20s %-30s %-10.2f%n",
                                item.getId(),
                                item.getFoodItemName(),
                                item.getDescription(),
                                item.getPrice());
                    }
                    System.out.print("Enter Item ID to add to order (or 0 to finish): ");
                    int itemId = scanner.nextInt();
                    if (itemId == 0) break;

                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();

                    for (FoodItem item : fm.getMenuItems()) {
                        if (item.getId() == itemId) {
                            total += item.getPrice() * qty;
                            break;
                        }
                    }
                }

                double discountedTotal = realDiscountManager.applyDiscounts(total);
                System.out.println("Original Total: ₹" + total);
                System.out.println("Discounted Total: ₹" + discountedTotal);
                System.out.println("Choose Payment: 1. Cash 2. UPI");
                int payChoice = scanner.nextInt();
                System.out.println(payChoice == 1 ? "Payment by Cash." : "Payment by UPI.");
                System.out.println("Thank you for your order!");
            }
        } else {
            System.out.println("Invalid user type.");
        }

        scanner.close();
    }
}
