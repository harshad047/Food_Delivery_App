package com.tss.model.payment;

import java.util.Scanner;

public class CreditCard implements IPayment {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long cardNumber;
    private String cardHolder;
    private int pin;

    public CreditCard(long cardNumber, String cardHolder, int pin) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.pin = pin;
    }

    @Override
    public void processPayment(double amount) {
        Scanner scanner = new Scanner(System.in);
        int attempt = 1;
        if (amount > 0) {
            while (attempt <= 5) {
                System.out.print("Enter PIN: ");
                int enteredPin = scanner.nextInt();
                if (pin == enteredPin) {
                    System.out.println("Processing Credit Card payment of $" + amount + " for card: " + cardNumber);
                    return;
                }
                if (attempt == 5) {
                    System.out.println("You are out of attempts.");
                    return;
                }
                System.out.println("Wrong PIN. Attempts left: " + (5 - attempt));
                attempt++;
            }
        } else {
            System.out.println("Enter valid amount");
        }
    }

    @Override
    public boolean validatePaymentDetails() {
        if (cardHolder == null || cardHolder.isEmpty()) {
            System.out.println("Validation failed: Card holder name is empty.");
            return false;
        }
        if (String.valueOf(cardNumber).length() != 16) {
            System.out.println("Validation failed: Card number must be 16 digits.");
            return false;
        }
        if (String.valueOf(pin).length() != 4) {
            System.out.println("Validation failed: PIN must be 4 digits.");
            return false;
        }
        return true;
    }
}
