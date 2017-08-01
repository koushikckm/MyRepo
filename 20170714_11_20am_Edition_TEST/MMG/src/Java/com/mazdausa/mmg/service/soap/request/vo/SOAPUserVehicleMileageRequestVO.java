/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This value object, will contain the details of the Vehicle Mileage request that needs to be sent to the web services.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_REQUEST_PARAMETER_MILEAGEDETAILS,namespace=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_REQUESTELEMENT_NAMESPACE)
public class SOAPUserVehicleMileageRequestVO {

    private String vin;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_REQUEST_PARAMETER_MILEAGEDETAILS_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    

}
