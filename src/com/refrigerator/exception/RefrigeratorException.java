package com.refrigerator.exception;

/**
 * @author Ashish.manjhi
 * 
 *         The Generic Exception when something goes wrong w.r.t to
 *         {@link Refrigerator}
 *
 */
public class RefrigeratorException extends RuntimeException {

	/** The serial version id. */
	private static final long serialVersionUID = -5562524247149085144L;

	public RefrigeratorException(String message) {
		super(message);
	}

}
