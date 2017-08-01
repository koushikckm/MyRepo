package com.mazda.exception;



/**
 * This is the main exception in caching framework.
 * @author Pradeep.Sharma
 * @since 30-01-2013
 * @version 1.0
 */
public class CachingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Class constructors
	/**
	 * Default constructor.  Constructs a new CachingException with null
	 * as its error message string.
	 */
	public CachingException() {
		// Call base class constructor
		super();
	}

	/**
	 * Constructs the object. Constructs a new CachingException with
	 * the specified error message.
	 *
	 * @param message the error message
	 */
	public CachingException(String message) {
		// Call base class constructor
		super(message);
	}

	/**
	 * Constructs the object. Constructs a new CachingException with
	 * the specified error message and nested exception.
	 *
	 *
	 * @param message the error message
	 * @param cause the nested exception
	 */
	public CachingException(String message, Throwable cause) {
		// Call base class constructor
		super(message);
		// Set the cause variable
		//this.setCause(cause);
	}

	/**
	 * Constructs the object. Constructs a new CachingException with
	 * the specified error message.
	 *
	 * @param cause the nested exception
	 */
	public CachingException(Throwable cause) {
		// Call base class constructor
		//this.setCause(cause);
	}

	// Setters and getters

	// Other methods

}

