package com.tss.model.discount;

public interface IDiscountManager {
    double applyDiscounts(double totalAmount);
    void setDiscount(IDiscount discount);
}
