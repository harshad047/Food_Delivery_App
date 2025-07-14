package com.tss.model;

import java.util.List;

import com.tss.model.food.FoodItem;
import com.tss.model.food.IMenu;

public class CustomerMenuProxy implements IMenu {
    private IMenu realMenu;

    public CustomerMenuProxy(IMenu realMenu) {
        this.realMenu = realMenu;
    }

    @Override
    public void addItem() {
        System.out.println("Customers cannot add items to the menu!");
    }

    @Override
    public void removeItem(int id) {
        System.out.println("Customers cannot remove items from the menu!");
    }

    @Override
    public void editItem(int id) {
        System.out.println("Customers cannot edit items in the menu!");
    }

    @Override
    public List<FoodItem> getMenuItems() {
        return realMenu.getMenuItems();
    }
}
