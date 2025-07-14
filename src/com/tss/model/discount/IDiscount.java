package com.tss.model.discount;

public interface IDiscount {
	double applyDiscount(double totalAmount);
    void setDiscountPercentage(double percentage);
    double getDiscountPercentage();
    String getDiscountType();
}
