package com.tss.model;

import java.util.Scanner;

public class DiscountManager implements IDiscountManager {
    private IDiscount regularDiscount = new RegularDiscount();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addDiscount() {
        System.out.print("Enter Regular Discount Percentage: ");
        double percentage = scanner.nextDouble();
        regularDiscount.setDiscountPercentage(percentage);
    }

    @Override
    public void removeDiscount() {
        regularDiscount.setDiscountPercentage(0);
        System.out.println("Regular discount removed.");
    }

    @Override
    public void editDiscount() {
        addDiscount();
    }

    @Override
    public void viewDiscounts() {
        System.out.println(regularDiscount.getDiscountType() + ": " + regularDiscount.getDiscountPercentage() + "%");
    }

    @Override
    public double applyDiscounts(double totalAmount) {
        return regularDiscount.applyDiscount(totalAmount);
    }
}
