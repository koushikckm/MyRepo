/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object, will represent the Dealer Address
 * @author Pankaj Bhatt
 * @version 1.0
 */
public class RestDealerInfoAddressVO {

    // List of the All the Sub-Elements of Dealer Address.
    private String street,city,state,zip,phone;

    @XmlElement(name=ApplicationConstants.XML_DEALER_ADDRESS_CITY)
    public String getCity() {
        return city.trim();
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name=ApplicationConstants.XML_DEALER_ADDRESS_PHONE)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name=ApplicationConstants.XML_DEALER_ADDRESS_STATE)
    public String getState() {
        return state.trim();
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlElement(name=ApplicationConstants.XML_DEALER_ADDRESS_STREET)
    public String getStreet() {
        return street.trim();
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @XmlElement(name=ApplicationConstants.XML_DEALER_ADDRESS_ZIP)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    
}
