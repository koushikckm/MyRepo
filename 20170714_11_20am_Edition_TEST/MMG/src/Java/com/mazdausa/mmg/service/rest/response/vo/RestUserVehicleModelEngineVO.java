/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO represent the Vehicle Model Engine details
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleModelEngineVO {

    private String engineDescription;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_ENGINE_ENGINEDESC)
    public String getEngineDescription() {
        return engineDescription;
    }

    public void setEngineDescription(String engineDescription) {
        this.engineDescription = engineDescription;
    }

    

}
