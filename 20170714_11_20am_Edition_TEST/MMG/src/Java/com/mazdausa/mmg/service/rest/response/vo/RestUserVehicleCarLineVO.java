/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO Contains the Carline details of the User Vehicle
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleCarLineVO {

    private String carLineCode,carLineDescription;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_CARLINE_CARLINECODE)
    public String getCarLineCode() {
        return carLineCode;
    }

    public void setCarLineCode(String carLineCode) {
        this.carLineCode = carLineCode;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_CARLINE_CARLINEDESC)
    public String getCarLineDescription() {
        return carLineDescription;
    }

    public void setCarLineDescription(String carLineDescription) {
        this.carLineDescription = carLineDescription;
    }



}
