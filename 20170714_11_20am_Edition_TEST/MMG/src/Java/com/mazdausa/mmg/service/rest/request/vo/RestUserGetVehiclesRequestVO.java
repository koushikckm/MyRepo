package com.mazdausa.mmg.service.rest.request.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;
@XmlRootElement(name = ApplicationConstants.XML_REST_GETVEHICLES_REQUEST_PARAMETER_GETVEHICLES, namespace = ApplicationConstants.SERVICE_REST_GETVEHICLES_REQUESTELEMENT_NAMESPACE)

public class RestUserGetVehiclesRequestVO {
	
	    private String customerId, requestor, version, requestId;

	    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_REQUEST_PARAMETER_GETVEHICLES_CUSTOMERID)
	    public String getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(String customerId) {
	        this.customerId = customerId;
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
