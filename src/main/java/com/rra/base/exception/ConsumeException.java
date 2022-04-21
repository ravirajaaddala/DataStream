package com.rra.base.exception;

public class ConsumeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsumeException(String message, Throwable t) {
		super("Consume exception happened due to "+message, t);
	}
}
