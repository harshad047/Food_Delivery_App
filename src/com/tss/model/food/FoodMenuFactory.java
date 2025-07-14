package com.tss.model.food;

import java.util.List;

public class FoodMenuFactory {
	List<FoodItem> menuItems;
	public IMenu chooseCousine(FoodCusine cousine)
	{
		if(cousine.equals(FoodCusine.ItalianMenu))
			return new ItalianMenu(menuItems);
		if(cousine.equals(FoodCusine.KoreanMenu))
			return new KoreanMenu(menuItems);
		if(cousine.equals(FoodCusine.IndianMenu))
			return new IndianMenu(menuItems);
		
		return null;
	}
}
