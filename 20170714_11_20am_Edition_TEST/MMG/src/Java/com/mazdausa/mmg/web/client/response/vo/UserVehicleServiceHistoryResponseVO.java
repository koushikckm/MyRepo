/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.service.rest.response.vo.VehicleServiceDetailsVO;

/**
 * This VO will contain the details of Vehicle History
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserVehicleServiceHistoryResponseVO {

    private String status, description;
    private VehicleServiceDetailsVO servicedetails;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VehicleServiceDetailsVO getServicedetails() {
        return servicedetails;
    }

    public void setServicedetails(VehicleServiceDetailsVO servicedetails) {
        this.servicedetails = servicedetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

     

    
    
    

}
