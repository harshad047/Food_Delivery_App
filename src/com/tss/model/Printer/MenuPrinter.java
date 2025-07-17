package com.tss.model.Printer;

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
                    .forEach(item -> {
                        String desc = item.getDescription();
                        if (desc.length() > 30) {
                            desc = desc.substring(0, 27) + "...";
                        }
                        System.out.printf(
                                "| %-3s | %-20s | %-10.2f | %-30s |%n",
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
