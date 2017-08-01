/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.service.rest.response.vo.RestVehicleAlertDetailResponseVO;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object will contain the details of the  Vehicle Alert Response to be sent to the client.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class VehcileRecallAlertResponseVO {

    private String status,error,description;
    private List<RestVehicleAlertDetailResponseVO> alerts;

   
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RestVehicleAlertDetailResponseVO> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<RestVehicleAlertDetailResponseVO> alerts) {
        this.alerts = alerts;
    }

    


}
