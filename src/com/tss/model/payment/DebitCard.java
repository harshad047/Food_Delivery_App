package com.tss.model.payment;

import java.util.Scanner;

public class DebitCard implements IPayment {

	private long cardNumber;
    private String cardHolder;
    private int pin;

    public DebitCard(long cardNumber, String cardHolder, int pin) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.pin = pin;
    }

    @Override
    public boolean processPayment(double amount) {
        Scanner scanner = new Scanner(System.in);
        int attempt = 1;
        if (amount > 0) {
            while (attempt <= 5) {
                System.out.print("Enter PIN: ");
                int enteredPin = scanner.nextInt();
                if (pin == enteredPin) {
                    return true;
                }
                if (attempt == 5) {
                    System.out.println("DebitCard Blocked");
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
