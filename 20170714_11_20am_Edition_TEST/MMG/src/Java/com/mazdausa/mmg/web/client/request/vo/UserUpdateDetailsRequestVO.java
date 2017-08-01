/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO will contain the details of the Request parameters that will be sent by the client to server, i.e. the list of parameters that will be
 * sent by client to Server.
 * @author PankajB
 */
@XmlRootElement
public class UserUpdateDetailsRequestVO {

    private String returnedEmail,addressValidation,addOnZip,source,error,custID,addressType,city,country,doNotCall,doNotEmail;
    private String email,emailType,firstName,homePhone,invalidAddressFlag,lastName,mobilePhone,state,street1Address,street2Address;
    private String workPhone,workPhoneExt,zip,doNotContact;

    public String getAddOnZip() {
        return addOnZip;
    }

    public void setAddOnZip(String addOnZip) {
        this.addOnZip = addOnZip;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressValidation() {
        return addressValidation;
    }

    public void setAddressValidation(String addressValidation) {
        this.addressValidation = addressValidation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getDoNotCall() {
        return doNotCall;
    }

    public void setDoNotCall(String doNotCall) {
        this.doNotCall = doNotCall;
    }

    public String getDoNotContact() {
        return doNotContact;
    }

    public void setDoNotContact(String doNotContact) {
        this.doNotContact = doNotContact;
    }

    public String getDoNotEmail() {
        return doNotEmail;
    }

    public void setDoNotEmail(String doNotEmail) {
        this.doNotEmail = doNotEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getInvalidAddressFlag() {
        return invalidAddressFlag;
    }

    public void setInvalidAddressFlag(String invalidAddressFlag) {
        this.invalidAddressFlag = invalidAddressFlag;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getReturnedEmail() {
        return returnedEmail;
    }

    public void setReturnedEmail(String returnedEmail) {
        this.returnedEmail = returnedEmail;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet1Address() {
        return street1Address;
    }

    public void setStreet1Address(String street1Address) {
        this.street1Address = street1Address;
    }

    public String getStreet2Address() {
        return street2Address;
    }

    public void setStreet2Address(String street2Address) {
        this.street2Address = street2Address;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getWorkPhoneExt() {
        return workPhoneExt;
    }

    public void setWorkPhoneExt(String workPhoneExt) {
        this.workPhoneExt = workPhoneExt;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }



}
