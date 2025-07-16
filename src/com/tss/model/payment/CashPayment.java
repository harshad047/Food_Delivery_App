package com.tss.model.payment;

public class CashPayment implements IPayment{

	

	@Override
	public boolean processPayment(double amount) {
		return true;
	}

	@Override
	public boolean validatePaymentDetails() {
		return true;
	}

}
