/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;

import javax.xml.bind.annotation.XmlElement;

/**
 * This will contain the Vehicle Service History Object
 * @author PankajB
 * @version 1.0
 */
public class VehicleServiceHistoryVO {

    private VehicleServiceDetailsVO vehicleServiceDetails;
    private String error;

    @XmlElement(name = ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS)
    public VehicleServiceDetailsVO getVehicleServiceDetails() {
        return vehicleServiceDetails;
    }

    public void setVehicleServiceDetails(VehicleServiceDetailsVO vehicleServiceDetails) {
        this.vehicleServiceDetails = vehicleServiceDetails;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_ERROR)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
