package com.tss.model;

public interface IDiscountManager {
    void addDiscount();
    void removeDiscount();
    void editDiscount();
    void viewDiscounts();
    double applyDiscounts(double totalAmount);
}
