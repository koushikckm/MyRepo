/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.service.rest.response.vo.GetVehiclesDetailsVehicleVO;

/**
 * This  Value object will represent the Detail Response of the User Vehicles  Request
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserVehiclesDetailResponseVO {

    private String error,description,count,status;
    private List<GetVehiclesDetailsVehicleVO> vehicles;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<GetVehiclesDetailsVehicleVO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<GetVehiclesDetailsVehicleVO> vehicles) {
        this.vehicles = vehicles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
