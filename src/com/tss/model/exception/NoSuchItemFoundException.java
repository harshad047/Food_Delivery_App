package com.tss.model.exception;

public class NoSuchItemFoundException extends RuntimeException{
	
	public String getMessage()
	{
		return "No Such Item Present In List";
		
	}
}
