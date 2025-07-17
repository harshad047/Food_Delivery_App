package com.tss.model.discount;

public interface IDiscount {
	double applyDiscount(double totalAmount);
    void setDiscountAmount(double amount);
    double getDiscountAmount();
    String getDiscountType();
}
