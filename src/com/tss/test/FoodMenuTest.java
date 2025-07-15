package com.tss.test;

import com.tss.model.proxy.AuthenticationProxy;

import java.util.Scanner;

public class FoodMenuTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        AuthenticationProxy.authenticateAndStart(username, password, scanner);

        scanner.close();
    }
}
