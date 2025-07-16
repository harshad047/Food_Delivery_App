package com.tss.model.payment;

import java.util.Scanner;

public class UPI implements IPayment {

	private String upiId;
	private int pin;

	public UPI(String upiId, int pin) {
		this.upiId = upiId;
		this.pin = pin;
	}

	@Override
	public boolean processPayment(double amount) {
		Scanner scanner = new Scanner(System.in);
		int attempt = 1;
		if (amount > 0) {
			while (attempt <= 5) {
				System.out.print("Enter PIN: ");
				int uPin = scanner.nextInt();
				if (pin == uPin) {
					return true;
				}
				if (attempt == 5) {
					System.out.println("You are out of attempts.");
					return false;
				}
				System.out.println("Wrong PIN. Attempts left: " + (5 - attempt));
				attempt++;
			}
		}
		return false;
	}

	@Override
	public boolean validatePaymentDetails() {
		if (upiId == null || upiId.isEmpty()) {
			System.out.println("Validation failed: UPI ID cannot be empty.");
			return false;
		}
		if (!(upiId.endsWith("@oksbi") || upiId.endsWith("@okaxis"))) {
			System.out.println("Validation failed: UPI ID must end with @oksbi or @okaxis.");
			return false;
		}
		String pinStr = String.valueOf(pin);
		if (pinStr.length() != 4) {
			System.out.println("Validation failed: PIN must be exactly 4 digits.");
			return false;
		}
		return true;
	}
}
