package com.tss.model.payment;

public class CashPayment implements IPayment{

	@Override
	public boolean processPayment(double amount) {
		System.out.println("Payment Is Done Through Cash :" + amount);
		return true;
	}

	@Override
	public boolean validatePaymentDetails() {
		return true;
	}

}
