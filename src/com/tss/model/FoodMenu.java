package com.tss.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.exception.ItemAlreadyExistsException;
import com.tss.exception.NoItemInListException;
import com.tss.exception.NoSuchItemFoundException;

public abstract class FoodMenu implements IMenu {
	protected List<FoodItem> menuItems;
	protected String cuisineName;
	protected Scanner scanner = new Scanner(System.in);

	public FoodMenu(List<FoodItem> menuItems, String cuisineName) {
		this.menuItems = menuItems=new ArrayList<>();;
		this.cuisineName = cuisineName;
		loadFromFile();
	}

	@Override
	public void addItem() {
		System.out.print("Enter Id Of Food Item: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		if (!menuItems.isEmpty()) {
			for (FoodItem foodItem : menuItems) {
				if (foodItem.getId() == id) {
					throw new ItemAlreadyExistsException(id, foodItem.getFoodItemName());
				}
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
		if (!isDeleted)
			throw new NoSuchItemFoundException();
	}

	@Override
	public void editItem(int id) {
		if (menuItems.isEmpty())
			throw new NoItemInListException();
		for (FoodItem item : menuItems) {
			if (item.getId() == id) {
				scanner.nextLine();
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
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cuisineName + "_menu.ser"))) {
			oos.writeObject(menuItems);
		} catch (IOException e) {
			System.out.println("Error saving menu: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	protected void loadFromFile() {
		File file = new File(cuisineName + "_menu.ser");
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				List<FoodItem> loaded = (List<FoodItem>) ois.readObject();
				menuItems.addAll(loaded);
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Error loading menu: " + e.getMessage());
			}
		}
	}
}
