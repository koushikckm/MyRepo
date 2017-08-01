package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE,namespace=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_REQUESTELEMENT_NAMESPACE)
public class RestUserVehicleServiceHistoryResponseVO {

	private VehicleServiceHistoryVO vehicleServiceHistory;

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN)
    public VehicleServiceHistoryVO getVehicleServiceHistory() {
        return vehicleServiceHistory;
    }

    public void setVehicleServiceHistory(VehicleServiceHistoryVO vehicleServiceHistory) {
        this.vehicleServiceHistory = vehicleServiceHistory;
    }
}
