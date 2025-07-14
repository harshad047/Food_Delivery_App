package com.tss.model.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.exception.ItemAlreadyExistsException;
import com.tss.model.exception.NoItemInListException;
import com.tss.model.exception.NoSuchItemFoundException;
import com.tss.model.repositary.Repositary;

public abstract class FoodMenu implements IMenu {
    protected List<FoodItem> menuItems;
    protected String cuisineName;
    protected Scanner scanner = new Scanner(System.in);

    public FoodMenu(List<FoodItem> menuItems, String cuisineName) {
        this.menuItems = new ArrayList<>();
        this.cuisineName = cuisineName;
        loadFromFile();
    }

    @Override
    public void addItem() {
        System.out.print("Enter Id Of Food Item: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (FoodItem foodItem : menuItems) {
            if (foodItem.getId() == id) {
                throw new ItemAlreadyExistsException(id, foodItem.getFoodItemName());
            }
        }
        System.out.println("Enter Name Of Food Item: ");
        String itemName = scanner.nextLine();
        System.out.println("Enter Description: ");
        String description = scanner.nextLine();
        System.out.println("Enter Price: ");
        double price = scanner.nextDouble();

        FoodItem item = new FoodItem(id, itemName, price, description);
        menuItems.add(item);
        saveToFile();
        System.out.println(itemName + " added to " + cuisineName + " Menu!");
    }

    @Override
    public void removeItem(int id) {
        boolean isDeleted = false;
        for (FoodItem item : menuItems) {
            if (item.getId() == id) {
                menuItems.remove(item);
                isDeleted = true;
                saveToFile();
                System.out.println(item.getFoodItemName() + " with ID " + id + " removed successfully!");
                break;
            }
        }
        if (!isDeleted) {
            throw new NoSuchItemFoundException();
        }
    }

    @Override
    public void editItem(int id) {
        if (menuItems.isEmpty()) {
            throw new NoItemInListException();
        }
        for (FoodItem item : menuItems) {
            if (item.getId() == id) {
               
                System.out.println("Enter Name Of Food Item: ");
                item.setFoodItemName(scanner.nextLine());
                System.out.println("Enter Description: ");
                item.setDescription(scanner.nextLine());
                System.out.println("Enter Price: ");
                item.setPrice(scanner.nextDouble());
                saveToFile();
                return;
            }
        }
        throw new NoSuchItemFoundException();
    }

    @Override
    public List<FoodItem> getMenuItems() {
        return this.menuItems;
    }

    protected void saveToFile() {
        Repositary.saveToFile(menuItems, cuisineName + "_menu.ser");
    }

    protected void loadFromFile() {
        List<FoodItem> loaded = Repositary.readFromFile(cuisineName + "_menu.ser");
        if (loaded != null) {
            menuItems.addAll(loaded);
        }
    }
}
