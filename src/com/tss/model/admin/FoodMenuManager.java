package com.tss.model.admin;

import java.util.Scanner;

import com.tss.model.Printer.MenuPrinter;
import com.tss.model.exception.ItemAlreadyExistsException;
import com.tss.model.exception.NoItemInListException;
import com.tss.model.exception.NoSuchItemFoundException;
import com.tss.model.exception.PriceInvalidException;
import com.tss.model.food.ChooseCousine;

import com.tss.model.food.IMenu;

public class FoodMenuManager {

	Scanner scanner = new Scanner(System.in);

    public FoodMenuManager() {
    }

    public void manageMenu() {
        
        IMenu menu = ChooseCousine.choose();

        boolean menuExit = false;
        while (!menuExit) {
            System.out.println("\n+-----------------------------+");
            System.out.println("|       Admin Food Menu       |");
            System.out.println("+-----------------------------+");
            System.out.printf("| 1. Add Item                 |\n");
            System.out.printf("| 2. Remove Item              |\n");
            System.out.printf("| 3. Edit Item                |\n");
            System.out.printf("| 4. View Menu                |\n");
            System.out.printf("| 5. Back                     |\n");
            System.out.println("+-----------------------------+");

            System.out.print("Choose: ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine();
            switch (menuChoice) {
                case 1 -> {
                    try {
                        menu.addItem();
                    } catch (ItemAlreadyExistsException | PriceInvalidException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        MenuPrinter.printMenu(menu);
                        menu.removeItem();
                    } catch (NoItemInListException | NoSuchItemFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        MenuPrinter.printMenu(menu);
                        System.out.print("Enter ID to edit: ");
                        menu.editItem(scanner.nextLine());
                        scanner.nextLine();
                    } catch (NoItemInListException | NoSuchItemFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> MenuPrinter.printMenu(menu);
                case 5 -> menuExit = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
