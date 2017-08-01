package com.mazdausa.mmg.service.rest.request.vo;


import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value object, will contain the details of input parameters that will be useful for containing Vehicle Service History
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_REQUEST_PARAMETER_SERVICEHISTORY)
public class RestUserVehicleServiceHistoryRequestVO {

    private String customerId, vin, requestor, version, requestId;

    @XmlElement(name = ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_REQUEST_PARAMETER_SERVICEHISTORY_CUSTID)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_REQUEST_PARAMETER_SERVICEHISTORY_VIN)
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
