package com.tss.exception;

public class ItemAlreadyExistsException extends RuntimeException{
	private int id;
	private String itemName;
	public ItemAlreadyExistsException(int id, String itemName) {
		super();
		this.id = id;
		this.itemName = itemName;
	}
	
	public String getMessage()
	{
		return "An item with ID " + id + " already exists! Which is "+ itemName;
	}
	
}
