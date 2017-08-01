package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

public class RestUserVehicleSetServicingDealerResponseVO {

	private String resultCode;

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_SERVICINGDEALER_RESPONSE_PARAMETER_UPDATESERVICINGDEALERRESPONSE_SERVICINGDEALERRETURN)
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
