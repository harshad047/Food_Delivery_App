package com.tss.model;

public interface IDiscount {
	double applyDiscount(double totalAmount);
    void setDiscountPercentage(double percentage);
    double getDiscountPercentage();
    String getDiscountType();
}
