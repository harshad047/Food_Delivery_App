package com.tss.model;

public class DiscountManagerProxy implements IDiscountManager {
    private IDiscountManager realManager;
    private String username;
    private String password;

    public DiscountManagerProxy(IDiscountManager realManager, String username, String password) {
        this.realManager = realManager;
        this.username = username;
        this.password = password;
    }

    private boolean isAuthorized() {
        return username.equals("harshad") && password.equals("Fggv@676");
    }

    @Override
    public void addDiscount() {
        if (isAuthorized()) realManager.addDiscount();
        else System.out.println("Unauthorized!");
    }

    @Override
    public void removeDiscount() {
        if (isAuthorized()) realManager.removeDiscount();
        else System.out.println("Unauthorized!");
    }

    @Override
    public void editDiscount() {
        if (isAuthorized()) realManager.editDiscount();
        else System.out.println("Unauthorized!");
    }

    @Override
    public void viewDiscounts() {
        realManager.viewDiscounts();
    }

    @Override
    public double applyDiscounts(double totalAmount) {
        return realManager.applyDiscounts(totalAmount);
    }
}
