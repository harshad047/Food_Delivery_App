package com.tss.model.payment;

import java.io.Serializable;

public interface IPayment extends Serializable{
	
	boolean processPayment(double amount);

	boolean validatePaymentDetails();
}
