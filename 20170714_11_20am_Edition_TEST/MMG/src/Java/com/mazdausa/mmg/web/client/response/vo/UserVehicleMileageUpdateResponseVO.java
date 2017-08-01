/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object, will contain the details of the Response of User Mileage Request from the client.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserVehicleMileageUpdateResponseVO {

    private String status,description;

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

    
    
}
