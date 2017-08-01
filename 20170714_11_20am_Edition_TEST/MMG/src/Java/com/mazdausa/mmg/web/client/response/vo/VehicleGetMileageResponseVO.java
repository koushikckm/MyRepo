/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.service.soap.response.vo.MileageDetailsVO;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the value object which will be returned to client.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class VehicleGetMileageResponseVO {

    private String status,description;
    private MileageDetailsVO mileagedetails;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MileageDetailsVO getMileagedetails() {
        return mileagedetails;
    }

    public void setMileagedetails(MileageDetailsVO mileagedetails) {
        this.mileagedetails = mileagedetails;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
