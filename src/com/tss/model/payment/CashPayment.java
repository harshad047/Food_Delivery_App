package com.tss.model.payment;

public class CashPayment implements IPayment {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void processPayment(double amount) {
        System.out.println("Collect " + amount + " in cash.");
    }

    @Override
    public boolean validatePaymentDetails() {
        // Cash does not need validation
        return true;
    }
}
