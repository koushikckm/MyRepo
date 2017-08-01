package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_RESPONSE_PARAMETER_UPDATECUSTOMERRESPONSE,namespace=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_RESPONSEELEMENT_NAMESPACE)
public class RestUserDetailsUpdateResponseVO {

	private String result;

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_RESPONSE_PARAMETER_UPDATECUSTOMERRESPONSE_UPDATECUSTOMERETURN)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
