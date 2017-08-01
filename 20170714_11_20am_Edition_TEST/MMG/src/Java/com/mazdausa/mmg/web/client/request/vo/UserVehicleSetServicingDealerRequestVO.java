/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO will contain the details of the Servicing Dealer Request sent by the client.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserVehicleSetServicingDealerRequestVO {

    private String customerId,dealerCode,source,vin;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
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
