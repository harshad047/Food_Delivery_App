package com.tss.model.food;

import java.util.Scanner;

public class ChooseCousine {
	static Scanner scanner = new Scanner(System.in);

	public static IMenu choose() {
		System.out.println("+-----------------------------------------+");
		System.out.println("|           Select Cuisine                |");
		System.out.println("+-----------------------------------------+");
		System.out.printf("| 1. Italian                              |\n");
		System.out.printf("| 2. Indian                               |\n");
		System.out.printf("| 3. Korean                               |\n");
		System.out.println("+-----------------------------------------+");

		System.out.print("Choose: ");
		int cuisineChoice = scanner.nextInt();
		scanner.nextLine();

		FoodCusine cusine = switch (cuisineChoice) {
		case 1 -> FoodCusine.ItalianMenu;
		case 2 -> FoodCusine.IndianMenu;
		case 3 -> FoodCusine.KoreanMenu;
		default -> null;
		};

		if (cusine == null) {
			System.out.println("Invalid cuisine. Try again.");
			return null;

		}

		return FoodMenuFactory.chooseCousine(cusine);
	}
}
