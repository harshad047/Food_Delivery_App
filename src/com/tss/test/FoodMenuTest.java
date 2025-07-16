package com.tss.test;

import com.tss.model.proxy.AuthenticationProxy;

import java.util.Scanner;

public class FoodMenuTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("+=====================================+");
        System.out.println("|         Welcome to Gir Gamthi!      |");
        System.out.println("+=====================================+\n");

        System.out.println("Choose Who You Are:");
        System.out.println("+-------------------------------------+");
        System.out.println("| 1. Admin Login                      |");
        System.out.println("| 2. Customer Login                   |");
        System.out.println("| 0. Exit                             |");
        System.out.println("+-------------------------------------+");
        System.out.print("Choose: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        String role = null;

        if (choice == 1) {
            role = "Admin";
        }

        if (choice == 2) {
            role = "Customer";
        }

        if (choice==0) {
        	System.out.println("Exiting !!");
        	scanner.close();
            return;
        }


        System.out.println("\n+-------------------------------------+");
        System.out.printf("           %s Login%n", role);
        System.out.println("+-------------------------------------+");
        System.out.print(" Username: ");
        String username = scanner.nextLine();
        System.out.print(" Password: ");
        String password = scanner.nextLine();
        System.out.println("+-------------------------------------+\n");

        System.out.println("Authenticating, please wait...\n");

        AuthenticationProxy.authenticateAndStart(role, username, password, scanner);

        scanner.close();
    }
}