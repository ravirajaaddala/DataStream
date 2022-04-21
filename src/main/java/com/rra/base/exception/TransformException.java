package com.rra.base.exception;

public class TransformException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransformException(String message, Throwable t) {
		super("Transformation exception happened due to "+message, t);
	}
}
