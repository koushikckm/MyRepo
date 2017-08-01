/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object holds, the detail of Mileage of a particular Vehicle.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSE_PARAMETER_MILEAGEDETAILS, namespace = ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSEELEMENT_NAMESPACE)
public class SOAPUserVehicleMileageResponseVO {

    private MileageDetailsVO mileageDetails;

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_RESPONSE_PARAMETER_MILEAGEDETAILS_MILEAGE)
    public MileageDetailsVO getMileageDetails() {
        return mileageDetails;
    }

    public void setMileageDetails(MileageDetailsVO mileageDetails) {
        this.mileageDetails = mileageDetails;
    }
}
