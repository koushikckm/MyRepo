/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author PankajB
 */
public class RestUserVehicleMaintenanceErrorVO {

    private String message,code;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ERROR_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ERROR_MESSAGE)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
