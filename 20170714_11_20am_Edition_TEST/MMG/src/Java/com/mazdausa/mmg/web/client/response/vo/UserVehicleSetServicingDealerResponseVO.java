/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO will contain the list of attributes, that are being sent by the Server to the client, in response of Set Servicing Dealer Request.
 * @author PankajB
 */
@XmlRootElement
public class UserVehicleSetServicingDealerResponseVO {

    private String status,description,code;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    
}
