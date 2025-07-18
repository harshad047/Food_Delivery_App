package com.tss.model.exception;

public class PriceInvalidException extends RuntimeException{
	
	public String getMessage()
	{
		return "Price Cannot Be Zero Or Negative";
	}
}
