package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_RESPONSE_PARAMETER_UPDATEOWNERSHIPRESPONSE,namespace=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_RESPONSEELEMENT_NAMESPACE)
public class RestUserVehicleUpdateOwnershipResponseVO {

	 private String resultCode;

	    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_RESPONSE_PARAMETER_UPDATEOWNERSHIPRESPONSE_UPDATEOWNSERSHIPRETURN)
	    public String getResultCode() {
	        return resultCode;
	    }

	    public void setResultCode(String resultCode) {
	        this.resultCode = resultCode;
	    }

}
