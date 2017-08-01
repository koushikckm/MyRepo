/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This will contain the details of the successful mileage update through Web Services.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_RESPONSE_PARAMETER_MILEAGERESPONSE,namespace=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_RESPONSEELEMENT_NAMESPACE)
public class SOAPUserVehicleMileageUpdateResponseVO {

    private String result;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_RESPONSE_PARAMETER_MILEAGERESPONSE_MILEAGERETURN)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    

}
