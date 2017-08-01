/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This Value object will contain all the details of the user as returned by the Web Service
 * @author PankajB
 * @version 1.0
 */
public class UserDetailResponseParameterVO {

    private String salesDealerId, error, customerId, dataInSynchFlag, existingCustomerFlag, addressType, city, country;
    private String doNotCall, doNotCallDate, doNotContact, doNotContactDate, doNotEmail, doNotEmailDate, email, emailType, firstName;
    private String homePhone, invalidAddressFlag, lastName, mobilePhone, state, street1address, street2address, workPhone, workPhoneExt, zip;

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_ADDRESSTYPE)
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_CITY)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_COUNTRY)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_CUSTID)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_DATAINSYNCHFLAG)
    public String getDataInSynchFlag() {
        return dataInSynchFlag;
    }

    public void setDataInSynchFlag(String dataInSynchFlag) {
        this.dataInSynchFlag = dataInSynchFlag;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_DONOTCALL)
    public String getDoNotCall() {
        return doNotCall;
    }

    public void setDoNotCall(String doNotCall) {
        this.doNotCall = doNotCall;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_DONOTCALLDATE)
    public String getDoNotCallDate() {
        return doNotCallDate;
    }

    public void setDoNotCallDate(String doNotCallDate) {
        this.doNotCallDate = doNotCallDate;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_DONOTCONTACT)
    public String getDoNotContact() {
        return doNotContact;
    }

    public void setDoNotContact(String doNotContact) {
        this.doNotContact = doNotContact;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_DONOTCONTACTDATE)
    public String getDoNotContactDate() {
        return doNotContactDate;
    }

    public void setDoNotContactDate(String doNotContactDate) {
        this.doNotContactDate = doNotContactDate;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_DONOTEMAIL)
    public String getDoNotEmail() {
        return doNotEmail;
    }

    public void setDoNotEmail(String doNotEmail) {
        this.doNotEmail = doNotEmail;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_DONOTEMAILDATE)
    public String getDoNotEmailDate() {
        return doNotEmailDate;
    }

    public void setDoNotEmailDate(String doNotEmailDate) {
        this.doNotEmailDate = doNotEmailDate;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_EMAILTYPE)
    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_ERROR)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_EXISTINGCUSTOMERFLAG)
    public String getExistingCustomerFlag() {
        return existingCustomerFlag;
    }

    public void setExistingCustomerFlag(String existingCustomerFlag) {
        this.existingCustomerFlag = existingCustomerFlag;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_FIRSTNAME)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_HOMEPHONE)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_INVALIDADDRESSFLAG)
    public String getInvalidAddressFlag() {
        return invalidAddressFlag;
    }

    public void setInvalidAddressFlag(String invalidAddressFlag) {
        this.invalidAddressFlag = invalidAddressFlag;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_LASTNAME)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_MOBILEPHONE)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_SALESDEALERID)
    public String getSalesDealerId() {
        return salesDealerId;
    }

    public void setSalesDealerId(String salesDealerId) {
        this.salesDealerId = salesDealerId;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_STATE)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_STREET1ADDRESS)
    public String getStreet1address() {
        return street1address;
    }

    public void setStreet1address(String street1address) {
        this.street1address = street1address;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_STREET2ADDRESS)
    public String getStreet2address() {
        return street2address;
    }

    public void setStreet2address(String street2address) {
        this.street2address = street2address;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_WORKPHONE)
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_WORKPHONEEXT)
    public String getWorkPhoneExt() {
        return workPhoneExt;
    }

    public void setWorkPhoneExt(String workPhoneExt) {
        this.workPhoneExt = workPhoneExt;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER_ZIP)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
