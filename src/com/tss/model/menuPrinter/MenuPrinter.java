package com.tss.model.menuPrinter;

import com.tss.model.food.FoodItem;
import com.tss.model.food.FoodMenu;
import com.tss.model.food.IMenu;

public class MenuPrinter {

    public static void printMenu(IMenu menu) {
        if (menu instanceof FoodMenu fm) {
            if (fm.getMenuItems().isEmpty()) {
                System.out.println("Menu is empty.");
                return;
            }

            System.out.printf("%-5s %-20s %-10s %-30s%n", "ID", "Name", "Price", "Description");
            fm.getMenuItems().stream()
                    .sorted((a, b) -> Integer.compare(a.getId(), b.getId()))
                    .forEach(item -> System.out.printf("%-5d %-20s %-10.2f %-30s%n",
                            item.getId(),
                            item.getFoodItemName(),
                            item.getPrice(),
                            item.getDescription()));
        } else {
            System.out.println("Invalid menu type.");
        }
    }
}
