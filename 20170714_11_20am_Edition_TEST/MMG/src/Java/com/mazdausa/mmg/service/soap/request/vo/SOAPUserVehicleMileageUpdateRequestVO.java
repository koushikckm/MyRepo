/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object will contain the details of the SOAP Vehicle Update request.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUEST_PARAMETER_SETMILEAGEDETAILS,namespace=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUESTELEMENT_NAMESPACE)
public class SOAPUserVehicleMileageUpdateRequestVO {

    private VehicleMileageUpdateVO vehicleMileageUpdate;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUEST_PARAMETER_SETMILEAGEDETAILS_SETMILEAGEDETAILS)
    public VehicleMileageUpdateVO getVehicleMileageUpdate() {
        return vehicleMileageUpdate;
    }

    public void setVehicleMileageUpdate(VehicleMileageUpdateVO vehicleMileageUpdate) {
        this.vehicleMileageUpdate = vehicleMileageUpdate;
    }

    

}
