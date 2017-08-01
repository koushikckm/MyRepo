package com.mazda.exception;

import java.util.ArrayList;
import java.util.List;

import com.mazda.common.transferobject.BusinessErrorTO;


/**
 * @author Pradeep.Sharma
 * @since 30-11-2012
 * @version 1.0
 * A base class for all business related exceptions.
 * 
 * <p>
 * <b>Overview: </b>
 * <p>
 * All business exceptions should inherit from <code>BusinessException</code>.
 * Exceptions can be defined based on business scenarios.
 */
public class BusinessException extends Exception {
	
	private List<BusinessErrorTO> errors = new ArrayList<BusinessErrorTO>(); 

	static final long serialVersionUID = -5829545098534135052L;
	
    public BusinessException(){}
    
    /**
     * a public constructor for <code>BusinessException</code>.
     * 
     * @param msg
     *            exception message.
     *  
     */
    public BusinessException(String message) {
        super(message);
    }

    public BusinessErrorTO[] getErrors()
    {
    	return (BusinessErrorTO[]) errors.toArray(new BusinessErrorTO[0]);
    }
    
    public void addError(BusinessErrorTO businessError)
    {
    	errors.add(businessError);
    }

  
}