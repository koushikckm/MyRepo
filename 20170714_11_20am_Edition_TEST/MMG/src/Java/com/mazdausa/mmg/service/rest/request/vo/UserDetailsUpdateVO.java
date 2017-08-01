/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will contain all the list of parameters that are being sent by the client to the Server.
 * @author PankajB
 * @version 1.0
 */
public class UserDetailsUpdateVO {

    private String returnedEmail,addressValidation,addOnZip,source,error,custID,addressType,city,country,doNotCall,doNotEmail;
    private String email,emailType,firstName,homePhone,invalidAddressFlag,lastName,mobilePhone,state,streetAddress1,streetAddress2;
    private String workPhone,workPhoneExt,zip,doNotContact, requestor, requestId, version;

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_ADDONZIP)
    public String getAddOnZip() {
        return addOnZip;
    }

    public void setAddOnZip(String addOnZip) {
        this.addOnZip = addOnZip;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_ADDRESSTYPE)
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_ADDRESSVALIDATION)
    public String getAddressValidation() {
        return addressValidation;
    }

    public void setAddressValidation(String addressValidation) {
        this.addressValidation = addressValidation;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_CITY)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_COUNTRY)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_CUSTOMERID)
    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_DONOTCALL)
    public String getDoNotCall() {
        return doNotCall;
    }

    public void setDoNotCall(String doNotCall) {
        this.doNotCall = doNotCall;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_DONOTCONTACT)
    public String getDoNotContact() {
        return doNotContact;
    }

    public void setDoNotContact(String doNotContact) {
        this.doNotContact = doNotContact;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_DONOTEMAIL)
    public String getDoNotEmail() {
        return doNotEmail;
    }

    public void setDoNotEmail(String doNotEmail) {
        this.doNotEmail = doNotEmail;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_EMAILTYPE)
    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_ERROR)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_FIRSTNAME)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_HOMEPHONE)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_INVALIDADDRESSFLAG)
    public String getInvalidAddressFlag() {
        return invalidAddressFlag;
    }

    public void setInvalidAddressFlag(String invalidAddressFlag) {
        this.invalidAddressFlag = invalidAddressFlag;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_LASTNAME)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_MOBILEPHONE)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_RETURNEDEMAIL)
    public String getReturnedEmail() {
        return returnedEmail;
    }

    public void setReturnedEmail(String returnedEmail) {
        this.returnedEmail = returnedEmail;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_SOURCE)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_STATE)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_STREETADDRESS1)
    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_STREETADDRESS2)
    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_WORKPHONE)
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_WORKPHONEEXT)
    public String getWorkPhoneExt() {
        return workPhoneExt;
    }

    public void setWorkPhoneExt(String workPhoneExt) {
        this.workPhoneExt = workPhoneExt;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER_ZIP)
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
