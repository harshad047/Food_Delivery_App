package com.tss.model.discount;

import java.io.Serializable;
import com.tss.model.repositary.Repositary;

public class RegularDiscount implements IDiscount, Serializable {
    private static final long serialVersionUID = 1L;

    private double discountAmount;

    public RegularDiscount() {
        this.discountAmount = 0;
    }

    @Override
    public double applyDiscount(double total) {
        return total - discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
        saveDiscount();
    }

    public double getDiscountAmount() {
        return this.discountAmount;
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
