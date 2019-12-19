package com.refrigerator.exception;

public class NotEnoughSpaceException extends RefrigeratorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughSpaceException(int itemId) {
		super(String.format("No space available in Refrigerator to accomodate item  with ID : %d ", itemId));
	}

}
