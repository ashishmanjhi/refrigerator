package com.refrigerator.exception;

/**
 * @author Ashish.manjhi
 * 
 *         Throw this exception when {@link Item} is not available in the
 *         {@link Refrigerator}
 *
 */
public class ItemNotFoundException extends RefrigeratorException {

	/** The serial version id. */
	private static final long serialVersionUID = -462064016638583646L;

	public ItemNotFoundException(int itemId) {
		super(String.format("Item with ID : %d cannot be found ", itemId));
	}

	public ItemNotFoundException(String itemName) {
		super(String.format("Item with Name : %s cannot be found ", itemName));
	}

}
