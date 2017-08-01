/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emm.v1.response.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PankajB
 */
@XmlRootElement
public class ClientGuidResponseVO {

    private String guid,description,status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    


}
