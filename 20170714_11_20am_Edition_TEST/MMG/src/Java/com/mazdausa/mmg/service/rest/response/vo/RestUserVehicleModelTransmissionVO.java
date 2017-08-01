/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will hold the details of the Vehicle Model Transmission.
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleModelTransmissionVO {

    private String transmissionDescription;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_TRANSMISSION_TRANSMISSIONDESC)
    public String getTransmissionDescription() {
        return transmissionDescription;
    }

    public void setTransmissionDescription(String transmissionDescription) {
        this.transmissionDescription = transmissionDescription;
    }

    

}
