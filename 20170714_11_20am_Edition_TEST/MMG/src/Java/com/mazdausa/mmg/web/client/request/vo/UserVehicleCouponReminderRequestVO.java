/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the Value object, that will contain the details of the Vehicle whose Coupon are required.
 * @author PankajB
 * @version
 */
@XmlRootElement
public class UserVehicleCouponReminderRequestVO {

    private String custId,source,vin;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    
}
