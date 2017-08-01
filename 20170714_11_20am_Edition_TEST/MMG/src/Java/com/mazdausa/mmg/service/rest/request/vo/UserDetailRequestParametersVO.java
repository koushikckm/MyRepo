/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Contain all the request parameter that will be sent and forms a sub element of the request.
 * @author PankajB
 * @version 1.0
 */
public class UserDetailRequestParametersVO {

    private String vin, customerId, email, firstName, lastName, source, streetAddress1, streetAddress2, zip, city, state, requestor, version, requestId;

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_CITY)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_CUSTID)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_FIRSTNAME)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_LASTNAME)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_SOURCE)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_STATE)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_STREET1ADDRESS)
    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_STREET2ADDRESS)
    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER_ZIP)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    @XmlElement(name = ApplicationConstants.SERVICE_REST_USER_REQUEST_PARAMETER_REQUESTOR)
	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	@XmlElement(name = ApplicationConstants.SERVICE_REST_USER_REQUEST_PARAMETER_VERSION)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlElement(name = ApplicationConstants.SERVICE_REST_USER_REQUEST_PARAMETER_REQUESTID)
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
