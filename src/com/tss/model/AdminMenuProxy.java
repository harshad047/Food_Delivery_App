package com.tss.model;

import java.util.List;

import com.tss.model.food.FoodItem;
import com.tss.model.food.IMenu;

public class AdminMenuProxy implements IMenu {
    private IMenu realMenu;
    private String username;
    private String password;

    public AdminMenuProxy(IMenu realMenu, String username, String password) {
        this.realMenu = realMenu;
        this.username = username;
        this.password = password;
    }

    private boolean isAuthorized() {
        return username.equals("harshad") && password.equals("Fggv@676");
    }

    @Override
    public void addItem() {
        if (isAuthorized()) {
            realMenu.addItem();
        } else {
            System.out.println("Unauthorized access! Only Admin can add items.");
        }
    }

    @Override
    public void removeItem(int id) {
        if (isAuthorized()) {
            realMenu.removeItem(id);
        } else {
            System.out.println("Unauthorized access! Only Admin can remove items.");
        }
    }

    @Override
    public void editItem(int id) {
        if (isAuthorized()) {
            realMenu.editItem(id);
        } else {
            System.out.println("Unauthorized access! Only Admin can edit items.");
        }
    }

    @Override
    public List<FoodItem> getMenuItems() {
        return realMenu.getMenuItems();
    }
}
