package com.rra.base.exception;

public class ProduceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProduceException(String message ) {
		super("produce exception happened due to "+message);
	}
	
	public ProduceException(String message, Throwable t) {
		super("produce exception happened due to "+message, t);
	}
}
