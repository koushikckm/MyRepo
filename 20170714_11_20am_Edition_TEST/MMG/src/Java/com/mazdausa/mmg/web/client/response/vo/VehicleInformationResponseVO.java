/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleVO;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value object, represent the JSON Variable that will be returned to the client in response of an Vehicle Information Query.
 * @author PankajB
 */
@XmlRootElement
public class VehicleInformationResponseVO {

    private String status,description;

    private VehicleInformationVO vehicle;

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

    
    public VehicleInformationVO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleInformationVO vehicle) {
        this.vehicle = vehicle;
    }

    


}
