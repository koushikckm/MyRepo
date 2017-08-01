/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO will contain the details of the Response that is to be sent to the client.
 * @author PankajB
 */
@XmlRootElement
public class UserUpdateDetailsResponseVO {

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
