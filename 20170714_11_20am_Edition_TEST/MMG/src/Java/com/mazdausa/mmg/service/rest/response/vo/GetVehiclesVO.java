/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This VO actually contains the error Object and vehicle details.
 * @author PankajB
 * @version 1.0
 */
public class GetVehiclesVO {

    private String error;
    private GetVehiclesDetailVO vehiclesDetail;

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_ERROR)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS)
    public GetVehiclesDetailVO getVehiclesDetail() {
        return vehiclesDetail;
    }

    public void setVehiclesDetail(GetVehiclesDetailVO vehiclesDetail) {
        this.vehiclesDetail = vehiclesDetail;
    }

    
}
