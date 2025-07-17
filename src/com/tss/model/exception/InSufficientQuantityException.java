package com.tss.model.exception;

public class InSufficientQuantityException extends RuntimeException{
	public String getMessage()
	{
		return "Quantity Is Insufficient For Remove";
	}
}
