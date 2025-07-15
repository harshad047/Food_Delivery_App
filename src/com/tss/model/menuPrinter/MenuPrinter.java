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

            String line = "+-----+----------------------+------------+--------------------------------+";
            System.out.println(line);
            System.out.printf("| %-3s | %-20s | %-10s | %-30s |%n", "ID", "Name", "Price", "Description");
            System.out.println(line);

            fm.getMenuItems().stream()
                    .sorted((a, b) -> Integer.compare(a.getId(), b.getId()))
                    .forEach(item -> {
                        String desc = item.getDescription();
                        // Truncate description to 30 characters if needed
                        if (desc.length() > 30) {
                            desc = desc.substring(0, 27) + "...";
                        }
                        System.out.printf(
                                "| %-3d | %-20s | %-10.2f | %-30s |%n",
                                item.getId(),
                                item.getFoodItemName(),
                                item.getPrice(),
                                desc
                        );
                    });

            System.out.println(line);
        } else {
            System.out.println("Invalid menu type.");
        }
    }
}
