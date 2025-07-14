package com.tss.model;

public class RegularDiscount implements IDiscount {
    private double percentage;

    @Override
    public double applyDiscount(double totalAmount) {
        if (totalAmount > 500) {
            return totalAmount - (totalAmount * (percentage / 100));
        }
        return totalAmount;
    }

    @Override
    public void setDiscountPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double getDiscountPercentage() {
        return percentage;
    }

    @Override
    public String getDiscountType() {
        return "Regular Discount";
    }
}
