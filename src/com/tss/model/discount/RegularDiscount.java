package com.tss.model.discount;

import java.io.Serializable;
import com.tss.model.repositary.Repositary;

public class RegularDiscount implements IDiscount, Serializable {
    private static final long serialVersionUID = 1L;

    private double discountPercentage;

    public RegularDiscount() {
        this.discountPercentage = 0.0;
    }

    @Override
    public double applyDiscount(double total) {
        return total * (1 - discountPercentage / 100);
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
        saveDiscount();
    }

    public double getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void saveDiscount() {
        Repositary.saveToFile(this, "regular_discount.ser");
    }

    public static RegularDiscount loadDiscount() {
        RegularDiscount discount = Repositary.readFromFile("regular_discount.ser");
        if (discount == null) {
            discount = new RegularDiscount();
        }
        return discount;
    }

    @Override
    public String getDiscountType() {
        return "Regular Discount";
    }
}
