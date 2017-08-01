/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO will contain the list of attributes that needs to be return to the Client based upon
 * @author PankajB
 */
@XmlRootElement
public class UserVehicleUpdateOwnershipResponseVO {

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
