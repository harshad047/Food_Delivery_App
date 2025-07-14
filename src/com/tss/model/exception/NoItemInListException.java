package com.tss.model.exception;

public class NoItemInListException extends RuntimeException{
	public String getMessage()
	{
		return "No ItemIn List";
	}
}
