package com.tss.model.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.exception.ItemAlreadyExistsException;
import com.tss.model.exception.NoItemInListException;
import com.tss.model.exception.NoSuchItemFoundException;
import com.tss.model.exception.PriceInvalidException;
import com.tss.model.repositary.Repositary;

public abstract class FoodMenu implements IMenu {
    protected List<FoodItem> menuItems;
    protected String cusineName;
    protected Scanner scanner = new Scanner(System.in);
    protected int id;

    public FoodMenu(List<FoodItem> menuItems, String cuisineName) {
        this.menuItems = new ArrayList<>();
        this.cusineName = cuisineName;
        loadFromFile();
    }

    private String generateId()
    {
    	int maxId = 0;
        for (FoodItem item : menuItems) {
            String idStr = item.getId().substring(2); 
            try {
                int itemId = Integer.parseInt(idStr);
                if (itemId > maxId) {
                    maxId = itemId;
                }
            } catch (NumberFormatException e) {
              
            }
        }
        this.id = maxId + 1;
        
        String foodId = cusineName.substring(0, 2).toUpperCase()+id;
        
        return foodId;
    }
    @Override
    public void addItem() {
    	String foodId = generateId();
        for (FoodItem foodItem : menuItems) {
            if (foodItem.getId().equalsIgnoreCase(foodId)) {
                throw new ItemAlreadyExistsException(id, foodItem.getFoodItemName());
            }
        }
        System.out.print("Enter Name Of Food Item: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if(price<=0)
        {
        	throw new PriceInvalidException();
        }
        FoodItem item = new FoodItem(foodId, itemName, price, description);
        menuItems.add(item);
        saveToFile();
        System.out.println(itemName + " added to " + cusineName + " Menu!");
    }

    @Override
    public void removeItem() {
    	if(menuItems.isEmpty())
    		throw new NoItemInListException();
    	System.out.print("Enter ID to remove: ");
    	String id = scanner.nextLine();
        boolean isDeleted = false;
        for (FoodItem item : menuItems) {
            if (item.getId().equalsIgnoreCase(id)) {
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
    public void editItem(String id) {
        if (menuItems.isEmpty()) {
            throw new NoItemInListException();
        }
        for (FoodItem item : menuItems) {
            if (item.getId().equalsIgnoreCase(id)) {
                System.out.print("Enter Name Of Food Item: ");
                item.setFoodItemName(scanner.nextLine());
                System.out.print("Enter Description: ");
                item.setDescription(scanner.nextLine());
                System.out.print("Enter Price: ");
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
        Repositary.saveToFile(menuItems, cusineName + "_menu.ser");
    }

    protected void loadFromFile() {
        List<FoodItem> loaded = Repositary.readFromFile(cusineName + "_menu.ser");
        if (loaded != null) {
            menuItems.addAll(loaded);
        }
    }
}
