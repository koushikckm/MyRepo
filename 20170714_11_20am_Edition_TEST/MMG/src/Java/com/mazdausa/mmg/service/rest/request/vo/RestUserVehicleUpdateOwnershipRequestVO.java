package com.mazdausa.mmg.service.rest.request.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP,namespace=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUESTELEMENT_NAMESPACE)
public class RestUserVehicleUpdateOwnershipRequestVO {

	 private UpdateVehicleOwnershipVO updateVehicleOwnershipVO;

	    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP_OWNER)
	    public UpdateVehicleOwnershipVO getUpdateVehicleOwnershipVO() {
	        return updateVehicleOwnershipVO;
	    }

	    public void setUpdateVehicleOwnershipVO(UpdateVehicleOwnershipVO updateVehicleOwnershipVO) {
	        this.updateVehicleOwnershipVO = updateVehicleOwnershipVO;
	    }
}
