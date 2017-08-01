/**
 * 
 */
package com.mazda.common.transferobject;

import java.io.Serializable;

/**
 * @author Pradeep.Sharma
 * @since 03-12-2012
 * @version 1.0
 * This transfer object class used to hold business exception objects
 *
 */
public class BusinessErrorTO implements Serializable{

	public static final long serialVersionUID = -5829545098534135052L;
	
	private String code;
	
	private String message;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
