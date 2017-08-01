/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will represent the exterior color information of the Vehicle
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleExtColorVO {

    private String extCode, extDesc;

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_EXTCOLOR_EXTCODE)
    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_EXTCOLOR_EXTDESC)
    public String getExtDesc() {
        return extDesc;
    }

    public void setExtDesc(String extDesc) {
        this.extDesc = extDesc;
    }
}
