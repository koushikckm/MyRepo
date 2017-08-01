/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will contain the description of interior color of the vehicle.
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleIntColorVO {

    private String intCode,intDesc;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_INTCOLOR_INTCODE)
    public String getIntCode() {
        return intCode;
    }

    public void setIntCode(String intCode) {
        this.intCode = intCode;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_INTCOLOR_INTDESC)
    public String getIntDesc() {
        return intDesc;
    }

    public void setIntDesc(String intDesc) {
        this.intDesc = intDesc;
    }

    
}
