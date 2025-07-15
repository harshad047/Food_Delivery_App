package com.tss.model.payment;

public interface IPayment {
	
	boolean processPayment(double amount);

	boolean validatePaymentDetails();
}
