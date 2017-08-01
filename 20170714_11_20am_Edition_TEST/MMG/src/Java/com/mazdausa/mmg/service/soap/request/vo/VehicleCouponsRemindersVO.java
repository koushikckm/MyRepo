/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object will contain the detail of the Coupons/Reminders fetching request from the Client
 * @author PankajB
 * @version 1.0
 */
public class VehicleCouponsRemindersVO {

    private String vin,source,custId;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUEST_PARAMETER_GETCOUPONDATA_CUSTOMERID)
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUEST_PARAMETER_GETCOUPONDATA_SOURCE)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUEST_PARAMETER_GETCOUPONDATA_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    
}
