package com.tss.model.payment;

public interface IPayment {

	void processPayment(double amount);

	boolean validatePaymentDetails();
}
