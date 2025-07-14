package com.tss.exception;

public class NoItemInListException extends RuntimeException{
	public String getMessage()
	{
		return "No ItemIn List";
	}
}
