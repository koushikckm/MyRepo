package com.mazdausa.mmg.service.rest.request.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_SERVICINGDEALER_REQUEST_PARAMETER_UPDATESERIVINGDEALER,namespace=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_SERVICINGDEALER_REQUESTELEMENT_NAMESPACE)
public class RestUserVehicleSetServicingDealerRequestVO {

	 private VehicleUpdateServicingDealerVO newServicingDealerVO;

	    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_SERVICINGDEALER_REQUEST_PARAMETER_UPDATESERIVINGDEALER_CUSTOMER)
	    public VehicleUpdateServicingDealerVO getNewServicingDealerVO() {
	        return newServicingDealerVO;
	    }

	    public void setNewServicingDealerVO(VehicleUpdateServicingDealerVO newServicingDealerVO) {
	        this.newServicingDealerVO = newServicingDealerVO;
	    }
}
