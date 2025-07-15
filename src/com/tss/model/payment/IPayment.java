package com.tss.model.payment;

import java.io.Serializable;

public interface IPayment extends Serializable{
	
	void processPayment(double amount);

	boolean validatePaymentDetails();
}
