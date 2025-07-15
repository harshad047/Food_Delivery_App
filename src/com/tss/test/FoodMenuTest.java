package com.tss.test;

import com.tss.model.proxy.AuthenticationProxy;

import java.util.Scanner;

public class FoodMenuTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("+=====================================+");
        System.out.println("|         Welcome to Gir Gamthi!      |");
        System.out.println("+=====================================+\n");

        System.out.println("+-------------------------------------+");
        System.out.println("|           Login to Continue         |");
        System.out.println("+-------------------------------------+");
        System.out.printf("| Username: ");
        String username = scanner.nextLine();
        System.out.printf("| Password: ");
        String password = scanner.nextLine();
        System.out.println("+-------------------------------------+\n");

        System.out.println("Authenticating, please wait...\n");

        AuthenticationProxy.authenticateAndStart(username, password, scanner);

        scanner.close();
    }
}
