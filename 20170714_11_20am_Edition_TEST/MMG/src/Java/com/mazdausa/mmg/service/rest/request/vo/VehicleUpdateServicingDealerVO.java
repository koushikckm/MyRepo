/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO Will contain he actual Set of properties to be sent to Web Service.
 * @author PankajB
 */
public class VehicleUpdateServicingDealerVO {


    
    private String  customerId,vin,dealerCode,source, requestor, requestId, version;

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_SERVICINGDEALER_REQUEST_PARAMETER_UPDATESERIVINGDEALER_CUSTOMER_CUSTOMERID)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_SERVICINGDEALER_REQUEST_PARAMETER_UPDATESERIVINGDEALER_CUSTOMER_DEALERCODE)
    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_SERVICINGDEALER_REQUEST_PARAMETER_UPDATESERIVINGDEALER_CUSTOMER_SOURCE)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_SERVICINGDEALER_REQUEST_PARAMETER_UPDATESERIVINGDEALER_CUSTOMER_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_REST_USER_REQUEST_PARAMETER_REQUESTOR)
	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	@XmlElement(name = ApplicationConstants.SERVICE_REST_USER_REQUEST_PARAMETER_VERSION)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlElement(name = ApplicationConstants.SERVICE_REST_USER_REQUEST_PARAMETER_REQUESTID)
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


}
